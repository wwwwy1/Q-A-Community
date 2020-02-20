package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Jobs;
import cn.Ideal.demo.util.JobUtil;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.SolrPage;
import cn.Ideal.demo.util.SolrUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		List<Jobs> jobs = (List<Jobs>)redisTemplate.opsForValue().get("jobs");
		List<Jobs> ret = JobUtil.getJobs();
		if (jobs==null){
			redisTemplate.opsForValue().set("jobs", ret);
			SolrUtil.batchSaveOrUpdate(ret);
		}
		return new Result("成功",200,null);
	}
	@GetMapping("/user/jobs")
	@ResponseBody
	public ModelAndView getData(ModelAndView mav,Integer current,String keyWords) throws IOException, SolrServerException {
		if (current==null)current = 1;
		if (keyWords==null || keyWords.equals("") || keyWords.equals("null"))
			keyWords = "";
		SolrPage solrPage = SolrUtil.queryHighlight(keyWords, current - 1, PAGE_SIZE);
		mav.getModel().put("data",solrPage);
		mav.getModel().put("keyWords",keyWords);
		mav.setViewName("/user/jobs");
		return mav;
	}

}
