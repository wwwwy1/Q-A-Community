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

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {
	private final String filePath = "F:\\eimg\\user\\"; // 上传后的路径
	private final String filePathSrc = "F:/eimg/"; // 无图上传后的路径

	@Autowired
	private IUserService iUserService;
	@Autowired
	private ITaskListService iTaskListService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@PostMapping(value = "checkUserName")
	public Boolean checkUserName(String userName) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", userName);
		int count = iUserService.count(queryWrapper);
		return count == 0;
	}

	@PostMapping(value = "checkUserEmail")
	public Boolean checkUserEmail(String userEmail) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_email", userEmail);
		int count = iUserService.count(queryWrapper);
		return count == 0;
	}

	@PostMapping(value = "add")
	public Result add(User user) {
		boolean save = iUserService.save(user);
		if (save) {
			return new Result("成功", 200, user);
		}
		return new Result("失败", 500, null);
	}

	@PostMapping(value = "update")
	public Result update(User user) {
		boolean b = iUserService.updateById(user);
		if (b) return new Result("成功", 200, user);
		return new Result("失败", 500, null);
	}

	@PostMapping(value = "loginCheck")
	public Result tologin(User user, HttpServletRequest request) {
		if (user == null) return new Result("失败", 500, null);
		return iUserService.login(user, request);
	}

	@GetMapping(value = "personal")
	public ModelAndView goPersional(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("/user/personal");
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		mav.getModel().put("data", byId);
		return mav;
	}

	@GetMapping(value = "taskList")
	public ModelAndView goTaskList(ModelAndView mav, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		Map<String, List<TaskList>> taskList = iTaskListService.getTaskList(userId);
		mav.getModel().put("data", byId);
		Collections.sort(taskList.get("dont"), (o1, o2) -> o1.getRank() - o2.getRank());
		Collections.sort(taskList.get("did"), (o1, o2) -> o1.getRank() - o2.getRank());
		Collections.sort(taskList.get("doing"), (o1, o2) -> o1.getRank() - o2.getRank());
		mav.getModel().put("dont", taskList.get("dont"));
		mav.getModel().put("doing", taskList.get("doing"));
		mav.getModel().put("did", taskList.get("did"));
		mav.setViewName("/user/taskList");
		return mav;
	}

	@GetMapping(value = "otherP/{userName}")
	public ModelAndView goOtherPersional(@PathVariable String userName, ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("/user/otherP");
		if (StringUtil.isNullOrSpace(userName)) mav.setViewName("/user/404");
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", userName);
		User byName = iUserService.getOne(queryWrapper);
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		mav.getModel().put("me", byId);
		if (byName == null) mav.setViewName("/user/404");
		mav.getModel().put("data", byName);
		return mav;
	}

	@PostMapping("uploadPhoto")
	public Result uploadPhoto(HttpServletRequest request, @RequestParam(value = "photo",required = false) MultipartFile file, String x, String y, String width, String height) {
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return new Result("未登录",400,null);
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		User byId = iUserService.getById(userId);
		String newFileName;
		String fileName;
		String userImg = "user/";
		if (file != null){
			fileName = file.getOriginalFilename();  // 文件名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
			fileName = UUID.randomUUID() + suffixName; // 新文件名
			File dest = new File(filePath + fileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {
				file.transferTo(dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 newFileName =  filePath + "cut" +fileName; // 新文件名
			userImg = "user/cut" +fileName;
			fileName = filePath + fileName;
		}else {
			fileName = filePathSrc + byId.getUserImg();
			newFileName =  fileName.split("[.]")[0]+"cut."+fileName.split("[.]")[1]; // 新文件名
			userImg = byId.getUserImg().split("[.]")[0]+"cut."+byId.getUserImg().split("[.]")[1];
		}
		byId.setUserImg(userImg);
		iUserService.updateById(byId);
		try {
			cut(Math.round(Float.valueOf(x)),Math.round(Float.valueOf(y)),Math.round(Float.valueOf(width)),Math.round(Float.valueOf(height)),fileName,newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			return new Result("失败", 500, null);
		}
		return new Result("成功", 200, null);
	}
	public void cut(int x, int y, int width, int height, String srcpath, String subpath) throws IOException {//裁剪方法
		FileInputStream is = null;
		ImageInputStream iis = null;
		String formatName = srcpath.split("[.]")[1];
		try {
			is = new FileInputStream(srcpath); //读取原始图片
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(formatName); //ImageReader声称能够解码指定格式
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is); //获取图片流
			reader.setInput(iis, true); //将iis标记为true（只向前搜索）意味着包含在输入源中的图像将只按顺序读取
			ImageReadParam param = reader.getDefaultReadParam(); //指定如何在输入时从 Java Image I/O框架的上下文中的流转换一幅图像或一组图像
			Rectangle rect = new Rectangle(x, y, width, height); //定义空间中的一个区域
			param.setSourceRegion(rect); //提供一个 BufferedImage，将其用作解码像素数据的目标。
			BufferedImage bi = reader.read(0, param); //读取索引imageIndex指定的对象
			ImageIO.write(bi, formatName, new File(subpath)); //保存新图片
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}
	}
}