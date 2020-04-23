package cn.Ideal.demo.schedule;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.SensitiveWords;
import cn.Ideal.demo.entity.Tags;
import cn.Ideal.demo.service.*;
import cn.Ideal.demo.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {
	@Autowired
	private ISensitiveWordsService iSensitiveWordsService;
	@Autowired
	private ScheduleTask scheduleTask;
	private static Logger logger = LoggerFactory.getLogger(StartService.class);
	private SensitiveWordsTrie sensitiveWordsTrie = SensitiveWordsTrie.INSTANCE;
	@Autowired
	private IForumService iForumService;
	@Autowired
	private ITagsService iTagsService;
	@Autowired
	private IReplyService iReplyService;
	@Autowired
	private IStatisticService iStatisticService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<SensitiveWords> list = iSensitiveWordsService.list();
		sensitiveWordsTrie.contentDefault(list);
		logger.info("=========== 项目启动后，初始化 字典树 =============");
		scheduleTask.getJobsInfo();
		logger.info("=========== 项目启动后，初始化 招聘信息 =============");
		List<Forum> forums = iForumService.list();
		for (Forum forum : forums) {
			redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,forum.getId().toString(),forum.getForumThumbs()+","+forum.getForumClicks()+","+forum.getForumReplys());
			Collection<Tags> tags = iTagsService.listByIds(StringUtil.getIdList(forum.getForumTips()));
			List<String> tagsList = new ArrayList<>();
			for (Tags tag : tags) {
				tagsList.add(tag.getTagsName());
			}
			forum.setForumTipNames(tagsList);
			forum.setAbbreviationContent(StringUtil.ignoreHtml(forum.getForumContent()));
		}
		SolrForumUtil.deleteAll();
		SolrForumUtil.batchSaveOrUpdate(forums);
		logger.info("=========== 项目启动后，初始化 论坛缓存信息 =============");
		List<Reply> replies = iReplyService.list();
		for (Reply reply : replies) {
			redisTemplate.opsForHash().put(RedisKeyEnum.REPLY_KEY,reply.getId().toString(),String.valueOf(reply.getReplyThumbs()));
		}
		logger.info("=========== 项目启动后，初始化 评论点赞信息 =============");
		List<Tags> tagsList = iTagsService.list();
		for (Tags tags : tagsList) {
			Integer id = tags.getId();
			tags.setLastWeekCount(iStatisticService.lastWeekCountByTagsId(id));
		}
		SolrTagUtil.deleteAll();
		SolrTagUtil.batchSaveOrUpdate(tagsList);


		logger.info("=========== 项目启动后，初始化 标签信息 =============");
	}

}

