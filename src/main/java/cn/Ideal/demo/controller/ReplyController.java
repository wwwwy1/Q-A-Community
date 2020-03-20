package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.IReplyService;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.RedisKeyEnum;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RestController
@RequestMapping("/")
public class ReplyController {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private IReplyService iReplyService;
	@Autowired
	private IUserService iUserService;

	@GetMapping("/user/openReplys/{id}")
	public ModelAndView openReplys(HttpServletRequest request, ModelAndView mav, @PathVariable("id")Integer id){
		Reply reply = iReplyService.getById(id);
		reply.setUser(iUserService.getById(reply.getReplyUserId()));
		QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("reply_father",id);
		queryWrapper.orderByAsc("insert_date");
		List<Reply> list = iReplyService.list(queryWrapper);
		// 加个用户信息
		for (Reply reply1 : list) {
			reply1.setUser(iUserService.getById(reply1.getReplyUserId()));
			reply1.setReUser(iUserService.getById(reply1.getReplyReturnUserId()));
		}
		mav.getModel().put("main",reply);
		mav.getModel().put("list",list);
		mav.setViewName("/user/openReplys");
		return mav;
	}
	@Transactional
	@PostMapping("/user/addReply")
	public Result addReply(HttpServletRequest request,Reply reply){
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return new Result("未登录",400,null);
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		User user = iUserService.getById(userId);
		reply.setReplyUserId(user.getId());
		String nums = (String)redisTemplate.opsForHash().get(RedisKeyEnum.FORUM_KEY, String.valueOf(reply.getForumId()));
		//赞，点击量，回复数
		String[] split = nums.split(",");
		int i = Integer.parseInt(split[2]);
		i++;
		redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,String.valueOf(reply.getForumId()),split[0]+","+split[1]+","+i);
		iReplyService.countReply(reply.getId());
		boolean save = iReplyService.save(reply);
		if (save){
			return new Result("新增成功",200,null);
		}else {
			return new Result("新增失败",500,null);
		}

	}
}
