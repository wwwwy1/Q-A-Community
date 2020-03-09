package cn.Ideal.demo.service;

import cn.Ideal.demo.entity.TaskList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-08
 */
public interface ITaskListService extends IService<TaskList> {
	Map<String,List<TaskList>> getTaskList(String userId);
	int deleteByUserId(String userId);
}
