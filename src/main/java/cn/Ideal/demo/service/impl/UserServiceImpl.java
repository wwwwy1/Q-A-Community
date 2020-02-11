package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.mapper.UserMapper;
import cn.Ideal.demo.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
