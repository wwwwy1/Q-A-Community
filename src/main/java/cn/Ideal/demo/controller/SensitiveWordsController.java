package cn.Ideal.demo.controller;


import cn.Ideal.demo.entity.SensitiveWords;
import cn.Ideal.demo.service.ISensitiveWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/sensitiveWords")
public class SensitiveWordsController {
	@Autowired
	private ISensitiveWordsService iSensitiveWordsService;
	@ResponseBody
	@GetMapping("get")
	public Object getById(Integer id){
		SensitiveWords byId = iSensitiveWordsService.getById(id);
		return byId;
	}
}
