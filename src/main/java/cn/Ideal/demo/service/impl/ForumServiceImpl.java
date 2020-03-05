package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.ThumbUp;
import cn.Ideal.demo.mapper.ForumMapper;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.service.IThumbUpService;
import cn.Ideal.demo.util.RedisKeyEnum;
import cn.Ideal.demo.util.SolrForumUtil;
import cn.Ideal.demo.util.SolrPage;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements IForumService {
	@Autowired
	private IForumService iForumService;
	@Autowired
	private IThumbUpService iThumbUpService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Override
	public SolrPage page(String userId,String keywords,Integer current,Integer pageSize) throws Exception{
		SolrPage solrPage = SolrForumUtil.queryHighlight(keywords, current, pageSize);
		List<Forum> data = (List<Forum>) solrPage.getData();
		for (Forum ent : data) {
			// 先从redis中获取值，若不存在，去数据库拿
			String redis = (String)redisTemplate.opsForHash().get(RedisKeyEnum.FORUM_KEY,String.valueOf(ent.getId()));
			if (!StringUtil.isNullOrSpace(redis)){
				// 赞，点击量，回复数
				String[] split = redis.split(",");
				ent.setForumThumbs(Integer.parseInt(split[0]));
				ent.setForumClicks(Integer.parseInt(split[1]));
				ent.setForumReplys(Integer.parseInt(split[2]));
			}else {
				Forum byId = iForumService.getById(ent.getId());
				ent.setForumReplys(byId.getForumReplys());
				ent.setForumClicks(byId.getForumClicks());
				ent.setForumThumbs(byId.getForumThumbs());
				redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,String.valueOf(ent.getId()),byId.getForumThumbs()+","+ byId.getForumClicks()+","+byId.getForumReplys());
			}
			if (StringUtil.isNullOrSpace(userId)){
				ent.setCanThumbUp(0);
			}else {
				String canThumbUp = iThumbUpService.getCanThumbUp(userId, ent.getId());
				if (StringUtil.isNullOrSpace(canThumbUp)){
					ent.setCanThumbUp(0);
				}else {
					ent.setCanThumbUp(1);
				}
			}
		}
		return solrPage;
	}

}
