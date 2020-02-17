package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Tags;
import cn.Ideal.demo.service.ITagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
@Controller
public class TagsController extends BaseController {
	@Autowired
	private ITagsService iTagsService;
	public static final Integer PAGE_SIZE = 12;
	@GetMapping(value = "/user/contacts")
	public ModelAndView goContacts(ModelAndView mav,String keyWords,Integer current,Integer rank){
		if (rank==null) rank = 1;
		QueryWrapper<Tags> queryWrapper = new QueryWrapper<>();
		if (keyWords!=null && !keyWords.equals("") && !keyWords.equals("null"))
			queryWrapper.like("tags_name",keyWords);
		else
			keyWords = "";
		if(current==null) current = 1;
		if (rank.equals(2))
			queryWrapper.orderByAsc("tags_name");
		else if (rank.equals(3))
			queryWrapper.orderByDesc("tags_count");
		IPage<Tags> page = new Page<>(current,PAGE_SIZE);
		IPage<Tags> ret = iTagsService.page(page, queryWrapper);
		mav.getModel().put("data",buildPage(ret));
		mav.getModel().put("keyWords",keyWords);
		mav.getModel().put("rank",rank);
		mav.setViewName("/user/contacts");
		return mav;
	}
}
