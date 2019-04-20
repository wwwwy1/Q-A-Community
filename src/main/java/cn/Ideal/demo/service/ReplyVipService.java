package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.ReplyvipMapper;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.Replyvip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyVipService {
    @Autowired
    private ReplyvipMapper replyvipMapper;
    public Map goOpenReplys(Integer rid){
        Map<String,Object> map=new HashMap<>();
        Replyvip re= replyvipMapper.selectByPrimaryKey(rid);
        List<Replyvip> replies=replyvipMapper.selectReply(rid);
        map.put("reply",replies);
        map.put("main",re);
        return map;
    }
}
