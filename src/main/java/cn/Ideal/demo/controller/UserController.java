package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.ResponseEntity;
import cn.Ideal.demo.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private IUserService iUserService;

	@PostMapping(value = "checkUserName")
	public Boolean checkUserName(String userName){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		int count = iUserService.count(queryWrapper);
		return count==0;
	}
	@PostMapping(value = "checkUserEmail")
	public Boolean checkUserEmail(String userEmail){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_email",userEmail);
		int count = iUserService.count(queryWrapper);
		return count==0;
	}
	@PostMapping(value = "add")
	public Result add(User user){
		boolean save = iUserService.save(user);
		if (save){
			return new Result("成功",200,user);
		}
		return new Result("失败",500,null);
	}
	@PostMapping(value = "loginCheck")
	public Result tologin(User user,HttpServletRequest request){
		if (user==null) return new Result("失败",500,null);
		return iUserService.login(user,request);
	}
//    @Autowi
   /*@PostMapping(value = "/user/loginCheck")
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
    }*/
}
