package cn.Ideal.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 检查每个到来的请求对应的session域中是否有登录标识
        Object loginName = request.getSession().getAttribute("username");
        if (null == loginName || !(loginName instanceof String)) {
            // 未登录，重定向到登录页
            try {
                response.sendRedirect("/user/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        String userName = (String) loginName;
        System.out.println("当前用户已登录，登录的用户名为： " + userName);
        return true;

    }
}
