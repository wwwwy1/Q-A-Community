package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
public interface UserMapper extends BaseMapper<User> {
	@Update("UPDATE user SET user_point = user_point+1 WHERE id = #{userId,jdbcType=VARCHAR} ")
	int addUserPoint(String userId);
	@Update("UPDATE user SET user_point = user_point-1 WHERE id = #{userId,jdbcType=VARCHAR} ")
	int removeUserPoint(String userId);

}
