package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.Tags;
import cn.Ideal.demo.mapper.TagsMapper;
import cn.Ideal.demo.service.ITagsService;
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
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

	@Override
	public int useTag(String tags) {
		return baseMapper.useTag(tags);
	}
}
