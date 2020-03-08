package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.TaskList;
import cn.Ideal.demo.mapper.TaskListMapper;
import cn.Ideal.demo.service.ITaskListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
