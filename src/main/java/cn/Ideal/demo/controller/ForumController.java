package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.SensitiveWordsTrie;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
@Controller
public class ForumController extends BaseController{
	private SensitiveWordsTrie sensitiveWordsTrie = SensitiveWordsTrie.INSTANCE;
	@Autowired
	private IForumService iForumService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	public static final Integer PAGE_SIZE = 10;

	@ResponseBody
	@PostMapping("/forum/add")
	public Result addForum(HttpServletRequest request, Forum forum){
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return new Result("未登录",400,null);
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		forum.setUserId(userId);
		String content = forum.getForumContent();
		forum.setForumContent(sensitiveWordsTrie.replaceSensitiveWords(content));
		iForumService.save(forum);
		return new Result("添加成功",200,forum);
	}
	/*@GetMapping("get")
	public ModelAndView get(ModelAndView mav,Integer id){
		mav.setViewName("/user/test");
		Forum byId = iForumService.getById(id);
		mav.getModel().put("con",byId.getForumContent());
		return mav;
	}*/
	@GetMapping(value = "/user/forum")
	public ModelAndView goForum(ModelAndView mav,@RequestParam(value = "current",defaultValue = "1",required = false)Integer current,
								@RequestParam(value = "keyWords",defaultValue = "",required = false) String keyWords,
								@RequestParam(value = "rank",defaultValue = "1",required = false)Integer rank){
		QueryWrapper<Forum> queryWrapper = new QueryWrapper<>();
		IPage<Forum> iPage = new Page<>(current,PAGE_SIZE);
		IPage<Forum> page = iForumService.page(iPage);
		//page.setRecords()
		mav.setViewName("/user/forum");
		mav.getModel().put("data",buildPage(page));
		return mav;
	}
}
