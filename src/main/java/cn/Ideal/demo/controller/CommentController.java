package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Comment;
import cn.Ideal.demo.service.CommentService;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping(value = "/user/createForum")
    public ResponseEntity createForum(Comment comment, MultipartFile imgGo, HttpServletRequest request){
        return commentService.createForum(comment,imgGo,request);
    }
    @GetMapping(value = "/user/forum")
    public ModelAndView goForum(){
        return commentService.goForum(1);
    }
    @GetMapping(value = "/user/forumVip")
    public ModelAndView goForumVip(){
        return commentService.goForumVip(1);
    }
    @GetMapping(value = "/user/forumDeatils/{cid}")
    public ModelAndView goForumDeatils(@PathVariable Integer cid){
        ModelAndView mav=new ModelAndView();
        mav.getModel().put("data",commentService.deatiles(cid));
        mav.setViewName("/user/forumDeatils");
        return mav;
    }
    @GetMapping(value = "/user/goPaging/{pageNum}")
    public Map goPaging(@PathVariable Integer pageNum){
        return commentService.goForumNot0(pageNum);
    }
    @GetMapping(value = "/user/search")
    public ModelAndView goSearch(String keywords){
        System.out.println("---123---");
        return commentService.goSearch(keywords);
    }
    @GetMapping(value = "/user/goSearchPaging")
    public Map<String, Object> goSearchPaging(String keywords,Integer pageNum){
        return commentService.search(keywords,pageNum);
    }

}
