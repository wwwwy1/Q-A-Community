package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.mapper.ForumMapper;
import cn.Ideal.demo.service.IForumService;
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
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements IForumService {

}
