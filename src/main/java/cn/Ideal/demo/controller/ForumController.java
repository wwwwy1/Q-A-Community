package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
@Controller
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	private IForumService iForumService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@ResponseBody
	@PostMapping("add")
	public Result addForum(HttpServletRequest request, Forum forum){
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return new Result("未登录",400,null);
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		forum.setUserId(userId);
		iForumService.save(forum);
		return new Result("添加成功",200,forum);
	}
	@GetMapping("get")
	public ModelAndView get(ModelAndView mav,Integer id){
		mav.setViewName("/user/test");
		Forum byId = iForumService.getById(id);
		mav.getModel().put("con",byId.getForumContent());
		return mav;
	}
}
