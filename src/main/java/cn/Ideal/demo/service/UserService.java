package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.UserMapper;
import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.util.MD5;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public ResponseEntity userLogin(User user, HttpServletRequest request){
        ResponseEntity re=new ResponseEntity();
        User checkUser= userMapper.selectByUsername(user.getUname());
        if (checkUser==null){
            re.setMsg("用户不存在");
            re.setStatus(2);
            return re;
        }else if (!checkUser.getUpwd().equals(MD5.md5(user.getUpwd()))){
            re.setMsg("密码错误");
            re.setStatus(1);
            return re;
        }
        re.setMsg("登录成功");
        re.setStatus(0);
        HttpSession session=request.getSession();
        session.setAttribute("userid",checkUser.getUid());
        session.setAttribute("username",user.getUname());
        return re;
    }
    public ResponseEntity insertUser(MultipartFile file, User user){
        ResponseEntity re=new ResponseEntity();
        user.setUpwd(MD5.md5(user.getUpwd()));
        if (file!=null){
            String profilesPath="F:\\eimg\\user\\";
            BufferedOutputStream out=null;
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString();
            uid=uid.replace("-","");
            String[] last=file.getOriginalFilename().split("[.]");
            String path=profilesPath+uid+"."+last[1];
            user.setUimg("user/"+uid+"."+last[1]);
            re.setStatus(userMapper.insert(user));
            try {
                File folder=new File(profilesPath);
                if (!folder.exists())folder.mkdirs();
                out = new BufferedOutputStream(new FileOutputStream(path));
                out.write(file.getBytes());
                out.flush();
            }catch (Exception e){
                e.printStackTrace();
                re.setMsg("badddd");
                return re;
            }finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            user.setUimg("user/timg.jpg");
            re.setStatus(userMapper.insert(user));
        }
        re.setMsg("新增成功");
        return re;
    }
}
