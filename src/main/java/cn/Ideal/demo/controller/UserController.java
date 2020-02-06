package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.UserService;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

   @PostMapping(value = "/user/loginCheck")
   public ResponseEntity userLogin(User user, HttpServletRequest request){
       System.out.println(user);
       ResponseEntity re= userService.userLogin(user,request);
       System.out.println(request.getSession().getAttribute("username"));
       return re;
   }
    @PostMapping(value = "/user/registerCheck")
    public ResponseEntity registerCheck(@RequestParam MultipartFile uimgfile, User user){
        ResponseEntity re=new ResponseEntity();
        System.out.println(user);
        if (uimgfile.isEmpty()) re= userService.insertUser(null,user);
        else  re= userService.insertUser(uimgfile,user);
        return re;
    }
}
