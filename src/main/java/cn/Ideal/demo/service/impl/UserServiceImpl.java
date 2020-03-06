package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.mapper.UserMapper;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.Result;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	@Override
	public Result login(User user,HttpServletRequest request) {
		QueryWrapper<User> byUsername = new QueryWrapper<>();
		byUsername.eq("user_name",user.getUserName());
		byUsername.eq("user_password",user.getUserPassword());
		User userByName = baseMapper.selectOne(byUsername);
		if (userByName == null){
			return new Result("用户名或密码错误",500,null);
		}
		log.info("UserServiceImpl-login: username-" + user.getUserName() + "password-" + user.getUserPassword());
		UUID uuid = UUID.fastUUID();
		// 登录后,把token返回到前台,并且把token保存到redis中
		redisTemplate.opsForValue().set(uuid.toString(),userByName.getId(),30,TimeUnit.MINUTES);
		userByName.setLastLoginDate(LocalDateTime.now());
		baseMapper.updateById(userByName);
		//request.setAttribute("token",uuid.toString());
		HttpSession session = request.getSession();
		session.setAttribute("tokenFront",uuid.toString());
		session.setAttribute("sessionUser",userByName);

		return new Result("登录成功",200,null);
	}

	@Override
	public int addUserPoint(String userId) {
		return baseMapper.addUserPoint(userId);
	}
}
