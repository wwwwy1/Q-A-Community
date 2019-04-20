package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.CommentvipMapper;
import cn.Ideal.demo.dao.ReplyvipMapper;
import cn.Ideal.demo.entity.Comment;
import cn.Ideal.demo.entity.Commentvip;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.Replyvip;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentVipService {
    @Autowired
    private CommentvipMapper commentvipMapper;
    @Autowired
    private ReplyvipMapper replyvipMapper;
    public ModelAndView goSearch(String keywords){
        ModelAndView mav=new ModelAndView();
        mav.getModel().put("data",keywords);
        mav.setViewName("/user/forumVip");
        return mav;
    }
    public Map<String,Object> search(String keywords, Integer pageNum){
        Map<String,Object> map=new HashMap<>();
        Page page=PageHelper.startPage(pageNum, 4);
        List<Commentvip> comments=commentvipMapper.selectKeywords(keywords);
        //System.out.println(comments);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Commentvip t:comments
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
    public Map<String,Object> deatiles(Integer cid){
        Commentvip comment= commentvipMapper.selectByPrimaryKey(cid);
        Map<String,Object> map=new HashMap<>();
        map.put("comment",comment);
        List<Replyvip> list= replyvipMapper.selectReply1(cid,0);
        map.put("replys",list);
        return map;
    }
}
