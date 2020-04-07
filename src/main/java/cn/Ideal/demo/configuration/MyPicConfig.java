package cn.Ideal.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
 public final static String WIN_USER = "file:F:\\eimg\\user\\";
 public final static String WIN_FORUM = "file:F:\\eimg\\forum\\";
 public final static String WIN_IMG = "file:F:\\eimg\\img\\";
 public final static String WIN_CESHI = "file:F:\\eimg\\ceshi\\";

 public final static String LINUX_USER = "file:/uar/eimg/user/";
 public final static String LINUX_FORUM = "file:/uar/eimg/forum/";
 public final static String LINUX_IMG = "file:/uar/eimg/img/";
 public final static String LINUX_CESHI = "file:/uar/eimg/ceshi/";


 @Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
 registry.addResourceHandler("/images/user/**").addResourceLocations(WIN_USER);
 registry.addResourceHandler("/images/forum/**").addResourceLocations(WIN_FORUM);
 registry.addResourceHandler("/images/img/**").addResourceLocations(WIN_IMG);
 //用户临时图片存放路径
 registry.addResourceHandler("/images/ceshi/**").addResourceLocations(WIN_CESHI);

}
}
