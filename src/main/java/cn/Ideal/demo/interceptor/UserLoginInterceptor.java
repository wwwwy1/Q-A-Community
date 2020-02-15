package cn.Ideal.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UserLoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

    	// 检查每个到来的请求对应的session域中是否有登录标识
		logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
		//从请求头中获取用户token（登陆凭证根据业务而定）
		String token = (String) request.getSession().getAttribute("tokenFront");
		String userId = "";
		if (token != null)
			userId = redisTemplate.opsForValue().get(token);
		if (userId != null && !userId.equals("")){
			redisTemplate.opsForValue().set(token,userId,30,TimeUnit.MINUTES);
			return true;
		}
		response.sendRedirect("/user/login");
		//这里的异常是我自定义的异常，系统抛出异常后框架捕获异常然后转为统一的格式返回给前端， 其实这里也可以返回false
		return false;

    }
}
