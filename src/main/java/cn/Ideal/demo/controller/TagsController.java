package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.Tags;
import cn.Ideal.demo.service.ITagsService;
import cn.Ideal.demo.util.SolrPage;
import cn.Ideal.demo.util.SolrTagUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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
	public ModelAndView goContacts(ModelAndView mav,String keyWords,Integer current,Integer rank) throws IOException, SolrServerException {
		if (rank==null) rank = 1;
		if(current==null)current = 1;
		SolrPage solrPage = SolrTagUtil.queryHighlight(keyWords, current, PAGE_SIZE, rank);
		mav.getModel().put("data",solrPage);
		mav.getModel().put("keyWords",keyWords);
		mav.getModel().put("rank",rank);
		mav.setViewName("/user/contacts");
		return mav;
	}
	@ResponseBody
	@GetMapping(value = "/tags/t")
	public int useTags(String ids){
		ids = "1,2,3,4";
		return iTagsService.useTag(ids);
	}
}
