package cn.Ideal.demo.schedule;

import cn.Ideal.demo.entity.SensitiveWords;
import cn.Ideal.demo.service.ISensitiveWordsService;
import cn.Ideal.demo.util.SensitiveWordsTrie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {
	@Autowired
	private ISensitiveWordsService iSensitiveWordsService;
	private static Logger logger = LoggerFactory.getLogger(StartService.class);
	private SensitiveWordsTrie sensitiveWordsTrie = SensitiveWordsTrie.INSTANCE;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<SensitiveWords> list = iSensitiveWordsService.list();
		sensitiveWordsTrie.contentDefault(list);
		logger.info("=========== 项目启动后，初始化 字典树 =============");
	}

}

