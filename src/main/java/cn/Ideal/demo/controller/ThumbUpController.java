package cn.Ideal.demo.controller;


import cn.Ideal.demo.util.RedisKeyEnum;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-02
 */
@Controller
@RequestMapping("/thumbUp")
public class ThumbUpController {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@ResponseBody
	@GetMapping("/up")
	public void thumbUp(HttpServletRequest request, @RequestParam(value = "forumId",defaultValue = "-1",required = false)Integer forumId,
						@RequestParam(value = "replyId",defaultValue = "-1",required = false)Integer replyId){
		if (replyId.equals(-1) && forumId.equals(-1))
			return;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return;
		String userId = redisTemplate.opsForValue().get(token);

		if (StringUtil.isNullOrSpace(userId)) return;
		// 将用户按赞信息存放到redis中
		String redisKey = RedisKeyEnum.THUMB_UP_FORUM;
		if (!replyId.equals(-1)) {
			redisKey = RedisKeyEnum.THUMB_UP_REPLY;
		}else {
			String nums = (String)redisTemplate.opsForHash().get(RedisKeyEnum.FORUM_KEY, String.valueOf(forumId));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			int i = Integer.parseInt(split[0]);
			i++;
			redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,String.valueOf(forumId),i+","+split[1]+","+split[2]);
		}
		String userResultString =  (String)redisTemplate.opsForHash().get(redisKey, userId);
		Set<Integer> userResult = StringUtil.stringToSet(userResultString);
		if (userResult == null) userResult = new HashSet<>();
		userResult.add(forumId);
		redisTemplate.opsForHash().put(redisKey, userId, StringUtil.setToString(userResult));


	}
	@ResponseBody
	@GetMapping("/cancel")
	public void thumbUpCancel(HttpServletRequest request, @RequestParam(value = "forumId",defaultValue = "-1",required = false)Integer forumId,
						@RequestParam(value = "replyId",defaultValue = "-1",required = false)Integer replyId){
		if (replyId.equals(-1) && forumId.equals(-1))
			return;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return;
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return;
		String redisKey = RedisKeyEnum.THUMB_UP_FORUM;
		if (!replyId.equals(-1)) {
			redisKey = RedisKeyEnum.THUMB_UP_REPLY;
		}else {
			String nums = (String)redisTemplate.opsForHash().get(RedisKeyEnum.FORUM_KEY, String.valueOf(forumId));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			int i = Integer.parseInt(split[0]);
			i=--i<0?0:i;
			redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,String.valueOf(forumId),i+","+split[1]+","+split[2]);
		}
		String userResultString =  (String)redisTemplate.opsForHash().get(redisKey, userId);
		Set<Integer> userResult = StringUtil.stringToSet(userResultString);
		if (userResult == null) userResult = new HashSet<>();
		userResult.remove(forumId);
		redisTemplate.opsForHash().put(redisKey, userId, StringUtil.setToString(userResult));
	}
}
