package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.CommentMapper;
import cn.Ideal.demo.dao.ReplyMapper;
import cn.Ideal.demo.entity.Comment;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.util.ResponseEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;
    /*
          * 接受用户数据，并调用dao，建立论坛
     */
    public ResponseEntity createForum(Comment comment, MultipartFile imgGo, HttpServletRequest request){
        ResponseEntity re=new ResponseEntity();
        re.setMsg("更改失败");
        if (imgGo!=null){
            System.out.println("------------有图片---------");
            String profilesPath="F:\\eimg\\forum\\";
            BufferedOutputStream out=null;
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString();
            uid=uid.replace("-","");
            String[] last=imgGo.getOriginalFilename().split("[.]");
            String path=profilesPath+uid+"."+last[1];
            comment.setCount(0);
            comment.setCimg("forum/"+uid+"."+last[1]);
            comment.setCtime(new Date());
            comment.setUid((Integer) request.getSession().getAttribute("userid"));
            commentMapper.insert(comment);
            re.setStatus(1);
            re.setMsg("更改成功");
            try {
                File folder=new File(profilesPath);
                if (!folder.exists())folder.mkdirs();
                out = new BufferedOutputStream(new FileOutputStream(path));
                out.write(imgGo.getBytes());
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
        }
        return re;
    }
    /*
     * 调用PageHelper插件，进行分页
     */
    public ModelAndView goForum(Integer pageNum){
        ModelAndView mav=new ModelAndView();
        Page page=PageHelper.startPage(pageNum, 4);
        List<Comment> comments=commentMapper.selectHot();
        mav.getModel().put("data",comments);
        mav.setViewName("/user/forum");
        return mav;
    }
    public ModelAndView goForumVip(Integer pageNum){
        ModelAndView mav=new ModelAndView();
        Page page=PageHelper.startPage(pageNum, 4);
        List<Comment> comments=commentMapper.selectHot();
        mav.getModel().put("data",comments);
        mav.setViewName("/user/forumVip");
        return mav;
    }
    public Map goForumNot0(Integer pageNum){
        Map<String,Object> map=new HashMap<>();
        Page page=PageHelper.startPage(pageNum, 4);
        List<Comment> comments=commentMapper.selectAll();
        //System.out.println(comments);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Comment t:comments
             ) {
            String dateString = formatter.format(t.getCtime());
            t.setC(dateString);
        }
        map.put("data",comments);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        map.put("pageNum",pageNum);
        return map;
    }
    /*
     * 利用mapper来查找第一层评论，并返回
     */
    public Map<String,Object> deatiles(Integer cid){
       Comment comment= commentMapper.selectByPrimaryKey(cid);
        Map<String,Object> map=new HashMap<>();
        map.put("comment",comment);
       List<Reply> list= replyMapper.selectReply1(cid,0);
       map.put("replys",list);
       return map;
    }

    public ModelAndView goSearch(String keywords){
        ModelAndView mav=new ModelAndView();
        mav.getModel().put("data",keywords);
        mav.setViewName("/user/search");
        return mav;
    }
    public Map<String,Object> search(String keywords,Integer pageNum){
        Map<String,Object> map=new HashMap<>();
        Page page=PageHelper.startPage(pageNum, 1);
        List<Comment> comments=commentMapper.selectKeywords(keywords);
        //System.out.println(comments);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Comment t:comments
                ) {
            String dateString = formatter.format(t.getCtime());
            t.setC(dateString);
        }
        map.put("data",comments);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        map.put("pageNum",pageNum);
        return map;
    }
}
