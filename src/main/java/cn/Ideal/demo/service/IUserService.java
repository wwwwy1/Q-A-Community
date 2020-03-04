package cn.Ideal.demo.service;

import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
public interface IUserService extends IService<User> {
	Result login(User user,HttpServletRequest request);
	int addUserPoint(String userId);
}
