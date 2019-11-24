package cn.Ideal.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping(value = "/user/createShop")
    public String goCreateShop(){
        return "/user/createShop";
    }
    @GetMapping(value = "/user/createLicense")
    public String goCreateLicense(){
        return "/user/createLicense";
    }
    @GetMapping(value = "/user/createQuotation")
    public String goCreateQuotation(){
        return "/user/createQuotation";
    }
    @GetMapping(value = "/user/cyxt")
    public String gocyxt(){
        return "/user/cyxt";
    }
    @GetMapping(value = "/user/zsjm")
    public String gozsjm(){
        return "/user/zsjm";
    }



}
