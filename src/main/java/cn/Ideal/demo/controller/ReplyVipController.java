package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.Replyvip;
import cn.Ideal.demo.service.ReplyVipService;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReplyVipController {
    @Autowired
    private ReplyVipService replyVipService;

    @GetMapping(value = "/user/openVipReplys/{rid}")
    public ModelAndView goOpenReplys(@PathVariable Integer rid){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/user/openVipReplys");
        mav.getModel().put("data",replyVipService.goOpenReplys(rid));
        return mav;
    }
    @PostMapping(value = "/user/addVipReply")
    public ResponseEntity addVipReply(Replyvip reply , HttpServletRequest request){
        System.out.println(reply);
        return    replyVipService.addReply(reply,request);
    }
    @PostMapping(value = "/user/addVipReplySon")
    public ResponseEntity addReplySon(Replyvip reply,HttpServletRequest request){
        return replyVipService.addReplySon(reply,request);
    }

}
