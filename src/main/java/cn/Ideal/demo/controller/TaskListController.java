package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.TaskList;
import cn.Ideal.demo.service.ITaskListService;
import cn.Ideal.demo.util.RedisKeyEnum;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-08
 */
@Controller
@RequestMapping("/taskList")
public class TaskListController {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private ITaskListService iTaskListService;

	@PostMapping("change")
	@ResponseBody
	public void changeTaksList(HttpServletRequest request,
							   String dontArray, String doingArray, String didArray){
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)){
			request.getSession().removeAttribute("tokenFront");
			return ;
		}
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) {
			request.getSession().removeAttribute("tokenFront");
			return;
		}
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("dontArray",dontArray);
		map.put("doingArray",doingArray);
		map.put("didArray",didArray);
		redisTemplate.opsForHash().delete(RedisKeyEnum.TASK_LIST_KEY,userId);
		redisTemplate.opsForHash().put(RedisKeyEnum.TASK_LIST_KEY,userId,map);


	}
}
