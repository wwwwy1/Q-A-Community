package cn.Ideal.demo.schedule;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.Job;
import cn.Ideal.demo.entity.ThumbUp;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.service.IThumbUpService;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.*;
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
	@Autowired
	private IUserService iUserService;
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
		//插入用户已经点赞的文章
		Map<Object, Object> thumbUpForum = redisTemplate.opsForHash().entries(RedisKeyEnum.THUMB_UP_FORUM);//thumbUpReply
		for (Map.Entry<Object, Object> entry : thumbUpForum.entrySet()) {
			String userId = (String)entry.getKey();
			String forumIds = (String)entry.getValue();
			HashSet<Integer> integers = StringUtil.stringToSet(forumIds);
			// 待插入数据库
			for (Integer forumId : integers) {
				iThumbUpService.save(new ThumbUp(userId,forumId));
				// 给用户增加赞（声望）
				Forum byId = iForumService.getById(forumId);
				iUserService.addUserPoint(byId.getUserId());
			}
		}
		//更新文章数据库中的赞，点击量，回复数
		Map<Object, Object> entries = redisTemplate.opsForHash().entries(RedisKeyEnum.FORUM_KEY);
		for (Map.Entry<Object, Object> entry : entries.entrySet()) {
			String forumId = (String)entry.getKey();
			String clicks = (String)entry.getValue();
			String[] split = clicks.split(",");
			iForumService.updateById(new Forum(Integer.valueOf(forumId),Integer.valueOf(split[2]),Integer.valueOf(split[1]),Integer.valueOf(split[0])));
		}
	}
}
