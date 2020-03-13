package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.Chat;
import cn.Ideal.demo.mapper.ChatMapper;
import cn.Ideal.demo.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-12
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

	@Override
	public int alreadyRead(String toUserName, String fromUserName) {
		return baseMapper.alreadyRead(toUserName,fromUserName);
	}
}
