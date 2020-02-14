package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Tags;
import cn.Ideal.demo.service.ITagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private ITagsService iTagsService;
    @GetMapping(value = "/user/index")
    public String goIndex(){
        return "/user/index";
    }
    @GetMapping(value = "/user/login")
    public String goLogin(){
        return "/user/login";
    }

    @GetMapping(value = "/user/forumLvMsg")
    public String goForumLvMsg(){
        return "/user/forumLvMsg";
    }

    @GetMapping(value = "/user/register")
    public String goRegister(){
        return "/user/register";
    }

    @GetMapping(value = "/user/posting")
    public ModelAndView goPosting(ModelAndView mav){
        QueryWrapper<Tags> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tags_enable",1);
        mav.getModel().put("list",iTagsService.list(queryWrapper));
        mav.setViewName("/user/posting");
        return mav;
    }
}
