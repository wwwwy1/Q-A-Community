package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Jobs;
import cn.Ideal.demo.util.JobUtil;
import cn.Ideal.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JobsController extends BaseController {
	@Autowired
	private RedisTemplate  redisTemplate;
	@GetMapping("/jobs/put")
	@ResponseBody
	public Result putData(){
		List<Jobs> jobs = (List<Jobs>)redisTemplate.opsForValue().get("jobs");
		if (jobs==null){
			redisTemplate.opsForValue().set("jobs", JobUtil.getJobs());
		}
		return new Result("成功",200,null);
	}
	@GetMapping("/user/jobs")
	@ResponseBody
	public ModelAndView getData(ModelAndView mav,Integer current){
		List<Jobs> jobs = (List<Jobs>)redisTemplate.opsForValue().get("jobs");
		mav.getModel().put("data",jobs);
		mav.setViewName("/user/jobs");
		return mav;
	}

}
