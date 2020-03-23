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
		// 将用户按赞取消存放到redis中
		Integer id = forumId.equals(-1)?replyId:forumId;
		String redisKey = RedisKeyEnum.THUMB_UP_FORUM;
		String redisDownKey = RedisKeyEnum.THUMB_DOWN_FORUM;
		String attrKey = RedisKeyEnum.FORUM_KEY;
		if (!replyId.equals(-1)) {
			redisKey = RedisKeyEnum.THUMB_UP_REPLY;
			redisDownKey = RedisKeyEnum.THUMB_DOWN_REPLY;
			attrKey = RedisKeyEnum.REPLY_KEY;
		}
		int i = 0;
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			i = Integer.parseInt(split[0]);
		}else {
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			i = Integer.parseInt(nums);
		}

		i++;
		// 取消表
		String userResultString =  (String)redisTemplate.opsForHash().get(redisDownKey, userId);
		Set<Integer> userResult = StringUtil.stringToSet(userResultString);
		if (userResult == null) userResult = new HashSet<>();
		if (userResult.contains(id)){
			i++;
			userResult.remove(id);
		}
		// 论坛/评论的按赞数
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),i+","+split[1]+","+split[2]);
		}else {
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),String.valueOf(i));
		}
		// 踩表添加信息
		String userUpString =  (String)redisTemplate.opsForHash().get(redisKey, userId);
		Set<Integer> userUp = StringUtil.stringToSet(userUpString);
		if (userUp == null) userUp = new HashSet<>();
		userUp.add(id);
		redisTemplate.opsForHash().put(redisKey, userId, StringUtil.setToString(userUp));
		redisTemplate.opsForHash().put(redisDownKey, userId, StringUtil.setToString(userResult));
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
		String redisDownKey = RedisKeyEnum.THUMB_DOWN_FORUM;
		String attrKey = RedisKeyEnum.FORUM_KEY;
		Integer id = forumId.equals(-1)?replyId:forumId;
		if (!replyId.equals(-1)) {
			redisKey = RedisKeyEnum.THUMB_UP_REPLY;
			redisDownKey = RedisKeyEnum.THUMB_DOWN_REPLY;
			attrKey = RedisKeyEnum.REPLY_KEY;

		}
		int i = 0;
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			i = Integer.parseInt(split[0]);
		}else {
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			i = Integer.parseInt(nums);
		}
		i--;
		// 论坛/评论的按赞数
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),i+","+split[1]+","+split[2]);
		}else {
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),String.valueOf(i));
		}
		String userResultString =  (String)redisTemplate.opsForHash().get(redisKey, userId);
		Set<Integer> userResult = StringUtil.stringToSet(userResultString);
		if (userResult == null) userResult = new HashSet<>();
		userResult.remove(id);
		redisTemplate.opsForHash().put(redisKey, userId, StringUtil.setToString(userResult));
	}
	@ResponseBody
	@GetMapping("/down")
	public void thumbDown(HttpServletRequest request, @RequestParam(value = "forumId",defaultValue = "-1",required = false)Integer forumId,
							  @RequestParam(value = "replyId",defaultValue = "-1",required = false)Integer replyId){
		if (replyId.equals(-1) && forumId.equals(-1))
			return;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return;
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return;
		// 将用户按赞取消存放到redis中
		Integer id = forumId.equals(-1)?replyId:forumId;
		String redisKey = RedisKeyEnum.THUMB_UP_FORUM;
		String redisDownKey = RedisKeyEnum.THUMB_DOWN_FORUM;
		String attrKey = RedisKeyEnum.FORUM_KEY;
		if (!replyId.equals(-1)) {
			redisKey = RedisKeyEnum.THUMB_UP_REPLY;
			redisDownKey = RedisKeyEnum.THUMB_DOWN_REPLY;
			attrKey = RedisKeyEnum.REPLY_KEY;

		}
		int i = 0;
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			i = Integer.parseInt(split[0]);
		}else {
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			i = Integer.parseInt(nums);
		}
		i--;
		// 点赞表
		String userResultString =  (String)redisTemplate.opsForHash().get(redisKey, userId);
		Set<Integer> userResult = StringUtil.stringToSet(userResultString);
		if (userResult == null) userResult = new HashSet<>();
		if (userResult.contains(id)){
			i--;
			userResult.remove(id);
		}
		// 论坛/评论的按赞数
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),i+","+split[1]+","+split[2]);
		}else {
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),String.valueOf(i));
		}
		// 踩表添加信息
		String userDownString =  (String)redisTemplate.opsForHash().get(redisDownKey, userId);
		Set<Integer> userDown = StringUtil.stringToSet(userDownString);
		if (userDown == null) userDown = new HashSet<>();
		userDown.add(id);
		redisTemplate.opsForHash().put(redisDownKey, userId, StringUtil.setToString(userDown));
		redisTemplate.opsForHash().put(redisKey, userId, StringUtil.setToString(userResult));

	}
	@ResponseBody
	@GetMapping("/cancelDown")
	public void thumbDownCancel(HttpServletRequest request, @RequestParam(value = "forumId",defaultValue = "-1",required = false)Integer forumId,
							  @RequestParam(value = "replyId",defaultValue = "-1",required = false)Integer replyId){
		if (replyId.equals(-1) && forumId.equals(-1))
			return;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return;
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return;
		Integer id = forumId.equals(-1)?replyId:forumId;
		String redisKey = RedisKeyEnum.THUMB_UP_FORUM;
		String redisDownKey = RedisKeyEnum.THUMB_DOWN_FORUM;
		String attrKey = RedisKeyEnum.FORUM_KEY;

		if (!replyId.equals(-1)) {
			redisKey = RedisKeyEnum.THUMB_UP_REPLY;
			redisDownKey = RedisKeyEnum.THUMB_DOWN_REPLY;
			attrKey = RedisKeyEnum.REPLY_KEY;
		}
		int i = 0;
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			i = Integer.parseInt(split[0]);
		}else {
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			i = Integer.parseInt(nums);
		}		//赞，点击量，回复数
		i++;
		// 论坛/评论的按赞数
		if (!attrKey.equals(RedisKeyEnum.REPLY_KEY)){
			String nums = (String)redisTemplate.opsForHash().get(attrKey, String.valueOf(id));
			//赞，点击量，回复数
			String[] split = nums.split(",");
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),i+","+split[1]+","+split[2]);
		}else {
			redisTemplate.opsForHash().put(attrKey,String.valueOf(id),String.valueOf(i));
		}
		String userResultString =  (String)redisTemplate.opsForHash().get(redisDownKey, userId);
		Set<Integer> userResult = StringUtil.stringToSet(userResultString);
		if (userResult == null) userResult = new HashSet<>();
		userResult.remove(id);
		redisTemplate.opsForHash().put(redisDownKey, userId, StringUtil.setToString(userResult));
	}

}
