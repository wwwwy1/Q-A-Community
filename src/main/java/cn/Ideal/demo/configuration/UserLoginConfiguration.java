package cn.Ideal.demo.configuration;

import cn.Ideal.demo.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class UserLoginConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration loginRegistry = registry.addInterceptor(interceptor());
        // 拦截路径
        loginRegistry.addPathPatterns("/user/*");
        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/user/login");
        loginRegistry.excludePathPatterns("/user/index");
        loginRegistry.excludePathPatterns("/user/loginCheck");
        loginRegistry.excludePathPatterns("/user/register");
        loginRegistry.excludePathPatterns("/user/registerCheck");
        //loginRegistry.excludePathPatterns("/user/posting");
        loginRegistry.excludePathPatterns("/user/checkUserName");
        loginRegistry.excludePathPatterns("/user/checkUserEmail");
        loginRegistry.excludePathPatterns("/user/contacts");
        loginRegistry.excludePathPatterns("/user/jobs");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/static/css/*.css");
        loginRegistry.excludePathPatterns("/static/js/*.*");
        loginRegistry.excludePathPatterns("/static/fonts/*.*");
        loginRegistry.excludePathPatterns("/image/login/*.png");
    }

    @Bean
    UserLoginInterceptor interceptor(){
        return new UserLoginInterceptor();
    }

}
