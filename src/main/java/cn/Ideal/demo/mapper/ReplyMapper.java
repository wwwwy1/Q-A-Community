package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.Reply;
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
public interface ReplyMapper extends BaseMapper<Reply> {
	@Update("UPDATE reply SET reply_count = reply_count+1 WHERE id = #{replyId,jdbcType=INTEGER} ")
	int countReply(Integer replyId);
}
