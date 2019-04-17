package cn.Ideal.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
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



}
