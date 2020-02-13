package cn.Ideal.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
 registry.addResourceHandler("/images/user/**").addResourceLocations("file:F:\\eimg\\user\\");
 registry.addResourceHandler("/images/forum/**").addResourceLocations("file:F:\\eimg\\forum\\");
 registry.addResourceHandler("/images/img/**").addResourceLocations("file:F:\\eimg\\img\\");
 //用户临时图片存放路径
 registry.addResourceHandler("/images/ceshi/**").addResourceLocations("file:F:\\eimg\\ceshi\\");

}
}
