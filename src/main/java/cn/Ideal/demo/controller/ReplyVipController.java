package cn.Ideal.demo.controller;

import cn.Ideal.demo.service.ReplyVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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
}
