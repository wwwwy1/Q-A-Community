package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.CommentMapper;
import cn.Ideal.demo.dao.ReplyMapper;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private CommentMapper commentMapper;
    /*
    * 接受用户信息并添加新评论
     */
    public ResponseEntity addReply(Reply reply ,HttpServletRequest request){
        ResponseEntity re=new ResponseEntity();
        reply.setUid((Integer) request.getSession().getAttribute("userid"));
        reply.setDtime(new Date());
        commentMapper.updateCount(reply.getCid());
        int c= replyMapper.insert(reply);
        if (c==1){
            re.setMsg("评论成功");
            re.setStatus(0);
        }else {
            re.setMsg("评论失败");
            re.setStatus(1);
        }
        return re;
    }
    /*
     * 接受评论的父节点，来显示评论具体内容
     */
    public Map goOpenReplys(Integer rid){
        Map<String,Object> map=new HashMap<>();
        Reply re= replyMapper.selectByPrimaryKey(rid);
        List<Reply> replies=replyMapper.selectReply(rid);
        map.put("reply",replies);
        map.put("main",re);
        return map;
    }
    /*
     * 接受评论的信息，返回评论结果
     */
    public ResponseEntity addReplySon(Reply reply,HttpServletRequest request){
        reply.setDtime(new Date());
        reply.setUid((Integer) request.getSession().getAttribute("userid"));
        commentMapper.updateCount(reply.getCid());
        Integer c= replyMapper.insert(reply);
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
