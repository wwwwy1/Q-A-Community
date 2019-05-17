package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.SimulationService;
import cn.Ideal.demo.service.UserService;
import cn.Ideal.demo.util.ResponseEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SimulationService simulationService;
   @PostMapping(value = "/user/loginCheck")
   public ResponseEntity userLogin(User user, HttpServletRequest request){
       System.out.println(user);
       ResponseEntity re= userService.userLogin(user,request);
       System.out.println(request.getSession().getAttribute("username"));
       simulationService.initProdutcAndwarehouse(request);
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
    @PostMapping(value = "/user/goPay")
    public ResponseEntity goPay(@RequestParam("price")Integer price,@RequestParam("cid") Integer cid,HttpServletRequest request){
       return userService.payVipComment(price,cid,request);
    }
}
