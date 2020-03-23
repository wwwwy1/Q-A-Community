package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.ThumbUp;
import cn.Ideal.demo.mapper.ThumbUpMapper;
import cn.Ideal.demo.service.IThumbUpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-03-02
 */
@Service
public class ThumbUpServiceImpl extends ServiceImpl<ThumbUpMapper, ThumbUp> implements IThumbUpService {

	@Override
	public Integer getCanThumbUp(String userId, Integer forumId) {
		return baseMapper.getCanThumbUp(userId,forumId);
	}
}
