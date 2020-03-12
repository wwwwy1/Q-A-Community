package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.TaskList;
import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.ITaskListService;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.ResponseEntity;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private IUserService iUserService;
	@Autowired
	private ITaskListService iTaskListService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
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
	@PostMapping(value = "update")
	public Result update(User user){
		boolean b = iUserService.updateById(user);
		if (b)	return new Result("成功",200,user);
		return new Result("失败",500,null);
	}
	@PostMapping(value = "loginCheck")
	public Result tologin(User user,HttpServletRequest request){
		if (user==null) return new Result("失败",500,null);
		return iUserService.login(user,request);
	}
	@GetMapping(value = "personal")
	public ModelAndView goPersional(ModelAndView mav,HttpServletRequest request){
		mav.setViewName("/user/personal");
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		mav.getModel().put("data",byId);
		return mav;
	}
	@GetMapping(value = "taskList")
	public ModelAndView goTaskList(ModelAndView mav,HttpServletRequest request){
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		Map<String, List<TaskList>> taskList = iTaskListService.getTaskList(userId);
		mav.getModel().put("data",byId);
		Collections.sort(taskList.get("dont"),(o1, o2) -> o1.getRank()-o2.getRank());
		Collections.sort(taskList.get("did"),(o1, o2) -> o1.getRank()-o2.getRank());
		Collections.sort(taskList.get("doing"),(o1, o2) -> o1.getRank()-o2.getRank());
		mav.getModel().put("dont",  taskList.get("dont"));
		mav.getModel().put("doing",taskList.get("doing"));
		mav.getModel().put("did",taskList.get("did"));
		mav.setViewName("/user/taskList");
		return mav;
	}
	@GetMapping(value = "otherP/{userName}")
	public ModelAndView goOtherPersional(@PathVariable String userName,ModelAndView mav,HttpServletRequest request){
		mav.setViewName("/user/otherP");
		if (StringUtil.isNullOrSpace(userName)) mav.setViewName("/user/404");
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		User byName = iUserService.getOne(queryWrapper);
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		mav.getModel().put("me",byId);
		if (byName==null) mav.setViewName("/user/404");
		mav.getModel().put("data",byName);
		return mav;
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
