package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.mapper.ReplyMapper;
import cn.Ideal.demo.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

	@Override
	public int countReply(Integer replyId) {
		return baseMapper.countReply(replyId);
	}
}
