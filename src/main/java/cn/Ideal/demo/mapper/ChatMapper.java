package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-12
 */
public interface ChatMapper extends BaseMapper<Chat> {
	@Update("update chat set is_read = 1 where to_user_name = #{toUserName,jdbcType=VARCHAR} and from_user_name = #{fromUserName,jdbcType=VARCHAR}")
	int alreadyRead(@Param("toUserName")String toUserName,@Param("fromUserName")String fromUserName);
}
