package cn.Ideal.demo.controller;

import cn.Ideal.demo.service.CommentVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@RestController
public class CommentVipController {
    @Autowired
    private CommentVipService commentVipservice;
    @GetMapping(value = "/user/forumVip")
    public ModelAndView goSearch(String keywords,HttpServletRequest request){
        System.out.println("---123---");
        return commentVipservice.goSearch(keywords,request);
    }
    @GetMapping(value = "/user/goSearchVipPaging")
    public Map<String, Object> goSearchVipPaging(String keywords, Integer pageNum, HttpServletRequest request){
        return commentVipservice.search(keywords,pageNum,request);
    }
    @GetMapping(value = "/user/forumVipDeatils/{cid}")
    public ModelAndView goForumDeatils(@PathVariable Integer cid){
        ModelAndView mav=new ModelAndView();
        mav.getModel().put("data",commentVipservice.deatiles(cid));
        mav.setViewName("/user/forumVipDeatils");
        return mav;
    }
}
