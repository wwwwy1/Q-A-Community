package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.service.ReplyService;
import cn.Ideal.demo.util.ResponseEntity;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping(value = "/user/addReply")
    public ResponseEntity addReply(Reply reply , HttpServletRequest request){
     return    replyService.addReply(reply,request);
    }
    @GetMapping(value = "/user/openReplys/{rid}")
    public ModelAndView goOpenReplys(@PathVariable Integer rid){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/user/openReplys");
        mav.getModel().put("data",replyService.goOpenReplys(rid));
        return mav;
    }
    @PostMapping(value = "/user/addReplySon")
    public ResponseEntity addReplySon(Reply reply,HttpServletRequest request){
        return replyService.addReplySon(reply,request);
    }
}
