package cn.Ideal.demo.schedule;

import cn.Ideal.demo.entity.Job;
import cn.Ideal.demo.entity.ThumbUp;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.service.IThumbUpService;
import cn.Ideal.demo.util.JobUtil;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.SolrUtil;
import cn.Ideal.demo.util.StringUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableScheduling
public class ScheduleTask {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private IThumbUpService iThumbUpService;
	@Autowired
	private IForumService iForumService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Scheduled(cron = "0 0 6 * * ?")  // 每天早上6点获取工作信息
	public void getJobsInfo() throws IOException, SolrServerException {
		redisTemplate.delete("jobs");
		List<Job> ret = JobUtil.getJobs();
		// 添加到缓存中
		redisTemplate.opsForValue().set("jobs", ret.toString());
		// 添加到solr中
		SolrUtil.deleteAll();
		SolrUtil.batchSaveOrUpdate(ret);
		logger.info("获取工作信息成功-定时任务");
	}
	@Transactional
	@Scheduled(cron = "0 0 0/1 * * ? ")
	public void redisDataToMySQL() {
		//1.更新文章总的点赞数
		Map<Object, Object> thumbUpForum = redisTemplate.opsForHash().entries("thumbUpForum");//thumbUpReply
		for (Map.Entry<Object, Object> entry : thumbUpForum.entrySet()) {
			String userId = (String)entry.getKey();
			String forumIds = (String)entry.getValue();
			HashSet<Integer> integers = StringUtil.StringToSet(forumIds);
			// 待插入数据库
			for (Integer integer : integers) {
				iThumbUpService.save(new ThumbUp(userId,integer));

			}
			integers.size();
		}

	}
}
