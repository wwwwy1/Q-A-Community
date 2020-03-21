package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.entity.Reply;
import cn.Ideal.demo.entity.Tags;
import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.IForumService;
import cn.Ideal.demo.service.IReplyService;
import cn.Ideal.demo.service.ITagsService;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public static final Integer PAGE_SIZE = 10;

	@ResponseBody
	@PostMapping("/forum/add")
	public Result addForum(HttpServletRequest request, Forum forum){
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
		mav.getModel().put("data",page);
		return mav;
	}
	@GetMapping(value = "/user/forum/{id}")
	public ModelAndView goForum(ModelAndView mav,@PathVariable Integer id){
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
		mav.getModel().put("forumData",byId);
		QueryWrapper<Reply> replyQueryWrapper = new QueryWrapper<>();
		replyQueryWrapper.eq("forum_id",id);
		replyQueryWrapper.eq("reply_father",0);
		List<Reply> list = iReplyService.list(replyQueryWrapper);
		for (Reply reply : list) {
			User byId1 = iUserService.getById(reply.getReplyUserId());
			reply.setUser(byId1);
		}
		mav.getModel().put("replyData",list);
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
