package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.*;
import cn.Ideal.demo.service.*;
import cn.Ideal.demo.util.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.Ideal.demo.util.RedisKeyEnum.STATISTIC_TAGS;
import static cn.Ideal.demo.util.RedisKeyEnum.STATISTIC_USER;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
@Controller
public class ForumController extends BaseController{
	private SensitiveWordsTrie sensitiveWordsTrie = SensitiveWordsTrie.INSTANCE;
	@Autowired
	private IForumService iForumService;
	@Autowired
	private ITagsService iTagsService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IReplyService iReplyService;
	@Autowired
	private IThumbUpService iThumbUpService;
	public static final Integer PAGE_SIZE = 5;

	@ResponseBody
	@PostMapping("/forum/add")
	public Result addForum(HttpServletRequest request, Forum forum) throws IOException, SolrServerException {
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) return new Result("未登录",400,null);
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		forum.setUserId(userId);
		String content = forum.getForumContent();
		forum.setForumContent(sensitiveWordsTrie.replaceSensitiveWords(content));
		// 添加标签使用次数
		iTagsService.useTag(forum.getForumTips());
		iForumService.save(forum);
		Collection<Tags> tags = iTagsService.listByIds(StringUtil.getIdList(forum.getForumTips()));
		List<String> tagsList = new ArrayList<>();
		for (Tags tag : tags) {
			tagsList.add(tag.getTagsName());
			if (redisTemplate.opsForHash().get(STATISTIC_TAGS,Integer.toString(tag.getId()))==null){
				redisTemplate.opsForHash().put(STATISTIC_TAGS,Integer.toString(tag.getId()),"1");
			}else {
				redisTemplate.opsForHash().increment(STATISTIC_TAGS,Integer.toString(tag.getId()),1);
			}
		}
		forum.setForumTipNames(tagsList);
		forum.setAbbreviationContent(StringUtil.ignoreHtml(forum.getForumContent()));
		SolrForumUtil.addForum(forum);
		return new Result("添加成功",200,forum);
	}
	/*@GetMapping("get")
	public ModelAndView get(ModelAndView mav,Integer id){
		mav.setViewName("/user/test");
		Forum byId = iForumService.getById(id);
		mav.getModel().put("con",byId.getForumContent());
		return mav;
	}*/
	@GetMapping(value = "/user/forum")
	public ModelAndView goForum(HttpServletRequest request,ModelAndView mav,@RequestParam(value = "current",defaultValue = "1",required = false)Integer current,
								@RequestParam(value = "keyWords",defaultValue = "",required = false) String keyWords,
								@RequestParam(value = "rank",defaultValue = "1",required = false)Integer rank) throws Exception {
		//QueryWrapper<Forum> queryWrapper = new QueryWrapper<>();
		/*IPage<Forum> iPage = new Page<>(current,PAGE_SIZE);
		IPage<Forum> page = iForumService.page(iPage);
		List<Forum> records = page.getRecords();
		for (int i = 0; i < records.size(); i++) {
			Collection<Tags> tags = iTagsService.listByIds(StringUtil.getIdList(records.get(i).getForumTips()));
			List<String> list = new ArrayList<>();
			for (Tags tag : tags) {
				list.add(tag.getTagsName());
			}
			records.get(i).setForumTipNames(list);
			records.get(i).setAbbreviationContent(StringUtil.ignoreHtml(records.get(i).getForumContent()));
		}*/
		String userId = null;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) {
			userId = null;
		}else {
			userId = redisTemplate.opsForValue().get(token);
		}
		//if (StringUtil.isNullOrSpace(userId)) return new Result("未登录",400,null);
		SolrPage page = iForumService.page(userId,keyWords, current, PAGE_SIZE);
		mav.setViewName("/user/forum");
		page.setKeyWords(StringUtil.isNullOrSpace(keyWords)?null:keyWords);
		mav.getModel().put("data",page);
		String s = redisTemplate.opsForValue().get(RedisKeyEnum.USER_TOP_LIST);
		List list = StringUtil.stringToObj(s, List.class);
		List<Statistic> topUsers = new ArrayList<>();
		if (list!=null){
			for (int i = 0; i < list.size(); i++) {
				User userId1 = iUserService.getById((String) ((JSONObject) list.get(i)).get("userId"));

				topUsers.add(new Statistic((String) ((JSONObject) list.get(i)).get("userId"),(Integer) ((JSONObject) list.get(i)).get("res"),userId1.getUserNickname()));
			}
		}
		mav.getModel().put("top",topUsers);
		return mav;
	}
	@GetMapping(value = "/user/forum/{id}")
	public ModelAndView goForum(ModelAndView mav,@PathVariable Integer id,HttpServletRequest request,
								@RequestParam(value = "current",defaultValue = "1",required = false)Integer current){
		String userId = null;
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) {
			userId = null;
		}else {
			userId = redisTemplate.opsForValue().get(token);
		}
		String nums = (String)redisTemplate.opsForHash().get(RedisKeyEnum.FORUM_KEY, String.valueOf(id));
		//赞，点击量，回复数
		String[] split = nums.split(",");
		int i = Integer.parseInt(split[1]);
		i++;
		redisTemplate.opsForHash().put(RedisKeyEnum.FORUM_KEY,String.valueOf(id),split[0]+","+i+","+split[2]);

		Forum byId = iForumService.getById(id);
		byId.setForumThumbs(Integer.valueOf(split[0]));
		byId.setForumClicks(i);
		byId.setForumReplys(Integer.valueOf(split[2]));
		// 点赞内容
		String forumResultString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_UP_FORUM, userId);
		Set<Integer> forumResult = StringUtil.stringToSet(forumResultString);
		String forumDownString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_DOWN_FORUM, userId);
		Set<Integer> forumDown = StringUtil.stringToSet(forumDownString);
		if (forumResult != null && forumResult.contains(byId.getId()) ) {
			byId.setCanThumbUp(1);
		}else if (forumDown != null && forumDown.contains(byId.getId())){
			byId.setCanThumbUp(2);
		}else {
			Integer canThumbUp = iThumbUpService.getCanThumbUp(userId, byId.getId());
			if (canThumbUp==null){
				byId.setCanThumbUp(0);
			}else {
				byId.setCanThumbUp(canThumbUp);
			}
		}
		mav.getModel().put("forumData",byId);
		QueryWrapper<Reply> replyQueryWrapper = new QueryWrapper<>();
		replyQueryWrapper.eq("forum_id",id);
		replyQueryWrapper.eq("reply_father",0);
		IPage<Reply> page = new Page<>(current,PAGE_SIZE);
		IPage<Reply> pageRet = iReplyService.page(page, replyQueryWrapper);
		List<Reply> list = pageRet.getRecords();
		for (Reply reply : list) {
			User byId1 = iUserService.getById(reply.getReplyUserId());
			reply.setUser(byId1);
			String result = (String) redisTemplate.opsForHash().get(RedisKeyEnum.REPLY_KEY, String.valueOf(reply.getId()));
			if (!StringUtil.isNullOrSpace(result)){
				reply.setReplyThumbs(Integer.parseInt(result));
			}else {
				redisTemplate.opsForHash().put(RedisKeyEnum.REPLY_KEY, String.valueOf(reply.getId()), String.valueOf(reply.getReplyThumbs()));
			}
			if (!StringUtil.isNullOrSpace(userId)){
				String userResultString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_UP_REPLY, userId);
				Set<Integer> userResult = StringUtil.stringToSet(userResultString);
				String userDownString =  (String)redisTemplate.opsForHash().get(RedisKeyEnum.THUMB_DOWN_REPLY, userId);
				Set<Integer> userDown = StringUtil.stringToSet(userDownString);
				if (userResult != null && userResult.contains(reply.getId()) ) {
					reply.setCanThumbUp(1);
				}else if (userDown != null && userDown.contains(reply.getId())){
					reply.setCanThumbUp(2);
				}else {
					Integer canThumbUp = iThumbUpService.getCanThumbUp(userId, reply.getId());
					if (canThumbUp==null){
						reply.setCanThumbUp(0);
					}else {
						reply.setCanThumbUp(canThumbUp);
					}
				}
			}
		}

		mav.getModel().put("replyData",list);
		SolrPage<Reply> emptyData = new SolrPage<>();
		emptyData.setPageNum(((Long)pageRet.getCurrent()).intValue());
		emptyData.setPages(((Long)pageRet.getPages()).intValue());
		mav.getModel().put("data",emptyData);
		mav.setViewName("/user/forumDeatils");
		Collection<Tags> tags = iTagsService.listByIds(StringUtil.getIdList(byId.getForumTips()));
		List<String> temp = new ArrayList<>();
		for (Tags tag : tags) {
			temp.add(tag.getTagsName());
		}
		byId.setForumTipNames(temp);
		return mav;
	}
}
