package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.service.IReplyService;
import cn.Ideal.demo.service.IThumbUpService;
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
import java.util.Set;

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
	private IForumService iForumService;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IThumbUpService iThumbUpService;

	@GetMapping("/user/openReplys/{id}")
	public ModelAndView openReplys(HttpServletRequest request, ModelAndView mav, @PathVariable("id")Integer id){
		String userId = null;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) {
			userId = null;
		}else {
			userId = redisTemplate.opsForValue().get(token);
		}
		Reply reply = iReplyService.getById(id);
		reply.setUser(iUserService.getById(reply.getReplyUserId()));
		QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("reply_father",id);
		queryWrapper.orderByAsc("insert_date");
		List<Reply> list = iReplyService.list(queryWrapper);
		String result1 = (String) redisTemplate.opsForHash().get(RedisKeyEnum.REPLY_KEY, String.valueOf(reply.getId()));
		if (!StringUtil.isNullOrSpace(result1)){
			reply.setReplyThumbs(Integer.parseInt(result1));
		}else {
			redisTemplate.opsForHash().put(RedisKeyEnum.REPLY_KEY, String.valueOf(reply.getId()), String.valueOf(reply.getReplyThumbs()));
		}
		if (!StringUtil.isNullOrSpace(userId)){
			String userResultString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_UP_REPLY, userId);
			Set<Integer> userResult = StringUtil.stringToSet(userResultString);
			String userDownString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_DOWN_REPLY, userId);
			Set<Integer> userDown = StringUtil.stringToSet(userDownString);
			if (userResult != null && userResult.contains(reply.getId()) ) {
				reply.setCanThumbUp(1);
			}else if (userDown != null && userDown.contains(reply.getId())){
				reply.setCanThumbUp(2);
			}else {
				Integer canThumbUp = iThumbUpService.getCanThumbUp(userId, reply.getId());
				if (canThumbUp==null){
					reply.setCanThumbUp(0);
				}else {
					reply.setCanThumbUp(canThumbUp);
				}
			}
		}
		// 加个用户信息
		for (Reply reply1 : list) {
			reply1.setUser(iUserService.getById(reply1.getReplyUserId()));
			reply1.setReUser(iUserService.getById(reply1.getReplyReturnUserId()));
			String result = (String) redisTemplate.opsForHash().get(RedisKeyEnum.REPLY_KEY, String.valueOf(reply1.getId()));
			if (!StringUtil.isNullOrSpace(result)){
				reply1.setReplyThumbs(Integer.parseInt(result));
			}else {
				redisTemplate.opsForHash().put(RedisKeyEnum.REPLY_KEY, String.valueOf(reply1.getId()), String.valueOf(reply1.getReplyThumbs()));
			}
			if (!StringUtil.isNullOrSpace(userId)){
				String userResultString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_UP_REPLY, userId);
				Set<Integer> userResult = StringUtil.stringToSet(userResultString);
				String userDownString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_DOWN_REPLY, userId);
				Set<Integer> userDown = StringUtil.stringToSet(userDownString);
				if (userResult != null && userResult.contains(reply1.getId()) ) {
					reply1.setCanThumbUp(1);
				}else if (userDown != null && userDown.contains(reply1.getId())){
					reply1.setCanThumbUp(2);
				}else {
					Integer canThumbUp = iThumbUpService.getCanThumbUp(userId, reply1.getId());
					if (canThumbUp==null){
						reply1.setCanThumbUp(0);
					}else {
						reply1.setCanThumbUp(canThumbUp);
					}
				}
			}
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
	@Transactional
	@PostMapping("/user/addReplySon")
	public Result addReplySon(Reply reply,HttpServletRequest request){
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return new Result("未登录",400,null);
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		reply.setReplyUserId(userId);
		iReplyService.save(reply);
		iReplyService.countReply(reply.getReplyFather());
		// 更新缓存，而不是表
		String nums = (String)redisTemplate.opsForHash().get(RedisKeyEnum.FORUM_KEY, String.valueOf(reply.getForumId()));
		//赞，点击量，回复数
		String[] split = nums.split(",");
		int i = Integer.parseInt(split[2]);
		i++;
		redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,String.valueOf(reply.getForumId()),split[0]+","+split[1]+","+i);

		iForumService.countForum(reply.getForumId());
		return new Result("新增成功",200,null);
	}
}
