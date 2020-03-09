package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.TaskList;
import cn.Ideal.demo.mapper.TaskListMapper;
import cn.Ideal.demo.service.ITaskListService;
import cn.Ideal.demo.util.RedisKeyEnum;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-08
 */
@Service
public class TaskListServiceImpl extends ServiceImpl<TaskListMapper, TaskList> implements ITaskListService {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Override
	public Map<String, List<TaskList>> getTaskList(String userId) {
		// 先去缓存里面拿，若是不存在去数据库中取，再放到缓存中
		Map<String,List<TaskList>> ans = new LinkedHashMap<>();
		String ret = (String)redisTemplate.opsForHash().get(RedisKeyEnum.TASK_LIST_KEY, userId);
		if (StringUtil.isNullOrSpace(ret) || ret.equals("{}")){
			List<TaskList> dont = baseMapper.getTaskList(userId, 1);
			List<TaskList> doing = baseMapper.getTaskList(userId, 2);
			List<TaskList> did = baseMapper.getTaskList(userId, 3);
			ans.put("dont",dont);
			ans.put("doing",doing);
			ans.put("did",did);
			redisTemplate.opsForHash().put(RedisKeyEnum.TASK_LIST_KEY,userId,StringUtil.objectToString(ans));
		}else {
			Map<String,String> map = StringUtil.stringToObj(ret, Map.class);
			List<TaskList> dont = StringUtil.jsonToTaskList(map.get("dont"), userId);
			List<TaskList> doing = StringUtil.jsonToTaskList(map.get("doing"), userId);
			List<TaskList> did = StringUtil.jsonToTaskList(map.get("did"), userId);
			ans.put("dont",dont);
			ans.put("doing",doing);
			ans.put("did",did);
		}
		return ans;
	}

	@Override
	public int deleteByUserId(String userId) {
		return baseMapper.deleteByUserId(userId);
	}
}
