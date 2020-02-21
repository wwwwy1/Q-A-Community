package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Job;
import cn.Ideal.demo.util.*;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class JobsController extends BaseController {
	@Autowired
	private RedisTemplate  redisTemplate;
	public static final Integer PAGE_SIZE = 12;
	@GetMapping("/jobs/put")
	@ResponseBody
	public Result putData() throws IOException, SolrServerException {
		Boolean jobs1 = redisTemplate.delete("jobs");
		List<Job> jobs = (List<Job>)redisTemplate.opsForValue().get("jobs");
		List<Job> ret = JobUtil.getJobs();
		if (jobs==null){
			redisTemplate.opsForValue().set("jobs", ret);
			SolrUtil.batchSaveOrUpdate(ret);
		}
		return new Result("成功",200,null);
	}
	@GetMapping("/user/jobs")
	@ResponseBody
	public ModelAndView getData(ModelAndView mav,Integer current,String keyWords,String location) throws IOException, SolrServerException {
		if (current==null)current = 1;
		if (StringUtil.isNullOrSpace(keyWords))
			keyWords = "";
		if (StringUtil.isNullOrSpace(location))
			location = "";

		SolrPage solrPage = SolrUtil.queryHighlight(StringUtil.removeSpace(keyWords),StringUtil.removeSpace(location), current - 1, PAGE_SIZE);
		mav.getModel().put("data",solrPage);
		mav.getModel().put("keyWords",keyWords);
		mav.getModel().put("location",location);
		mav.setViewName("/user/jobs");
		return mav;
	}

}
