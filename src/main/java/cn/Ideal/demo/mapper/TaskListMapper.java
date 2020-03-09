package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.TaskList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-08
 */
public interface TaskListMapper extends BaseMapper<TaskList> {
	@Select("Select * from task_list WHERE user_id = #{userId,jdbcType=VARCHAR} and type = #{type,jdbcType=INTEGER}")
	List<TaskList> getTaskList(@Param("userId")String userId,@Param("type")Integer type);
	@Update("update task_list set is_deleted = 1 where user_id = #{userId,jdbcType=VARCHAR}")
	int deleteByUserId(String userId);
}
