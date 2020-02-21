package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Job;
import cn.Ideal.demo.util.JobUtil;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.SolrUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleTask {
	@Autowired
	private RedisTemplate redisTemplate;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Scheduled(cron = "0 0 6 * * ?")  // 每天早上6点获取工作信息
	public void getJobsInfo() throws IOException, SolrServerException {
		redisTemplate.delete("jobs");
		List<Job> ret = JobUtil.getJobs();
		// 添加到缓存中
		redisTemplate.opsForValue().set("jobs", ret);
		// 添加到solr中
		SolrUtil.batchSaveOrUpdate(ret);
		logger.info("获取工作信息成功-定时任务");
	}
}
