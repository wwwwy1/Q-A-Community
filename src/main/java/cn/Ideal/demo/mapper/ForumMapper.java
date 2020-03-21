package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.Forum;
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
public interface ForumMapper extends BaseMapper<Forum> {
	@Update("UPDATE forum SET forum_replys = forum_replys+1 WHERE id = #{forumId,jdbcType=INTEGER} ")
	int countForum(Integer forumId);
}
