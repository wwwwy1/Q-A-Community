package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.CommentvipMapper;
import cn.Ideal.demo.dao.ReplyvipMapper;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.Replyvip;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyVipService {
    @Autowired
    private ReplyvipMapper replyvipMapper;
    @Autowired
    private CommentvipMapper commentvipMapper;
    public Map goOpenReplys(Integer rid){
        Map<String,Object> map=new HashMap<>();
        Replyvip re= replyvipMapper.selectByPrimaryKey(rid);
        List<Replyvip> replies=replyvipMapper.selectReply(rid);
        map.put("reply",replies);
        map.put("main",re);
        return map;
    }
    public ResponseEntity addReply(Replyvip reply , HttpServletRequest request){
        ResponseEntity re=new ResponseEntity();
        reply.setUid((Integer) request.getSession().getAttribute("userid"));
        reply.setDtime(new Date());
        System.out.println(reply.getCid());
        int d=commentvipMapper.updateCount(reply.getCid());
        System.out.println(d);
        int c= replyvipMapper.insert(reply);
        if (c==1){
            re.setMsg("评论成功");
            re.setStatus(0);
        }else {
            re.setMsg("评论失败");
            re.setStatus(1);
        }
        return re;
    }
    public ResponseEntity addReplySon(Replyvip reply,HttpServletRequest request){
        reply.setDtime(new Date());
        reply.setUid((Integer) request.getSession().getAttribute("userid"));
        commentvipMapper.updateCount(reply.getCid());
        Integer c= replyvipMapper.insert(reply);
        ResponseEntity re=new ResponseEntity();
        if (c==1){
            re.setMsg("评论成功");
            re.setStatus(0);
        }else {
            re.setMsg("评论失败");
            re.setStatus(1);
        }
        return re;
    }
}
