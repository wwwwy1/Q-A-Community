package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.ThumbUp;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-02
 */
public interface ThumbUpMapper extends BaseMapper<ThumbUp> {
	@Select("select user_id from thumb_up where user_id = #{userId,jdbcType=VARCHAR} and forum_id = #{forumId,jdbcType=INTEGER} and reply_id is NULL")
	String getCanThumbUp(@Param("userId") String userId, @Param("forumId") Integer forumId);
}
