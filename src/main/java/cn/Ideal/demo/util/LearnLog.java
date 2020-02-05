package cn.Ideal.demo.util;

public enum  LearnLog {
	/*
	 * 学习日志：10月1日开始
	 * 日期，内容
	 * */
	LearnConcurrent1("2019/10/1","学习<java并发编程的艺术> 第一章第二章第四章1/2"),
	LearnConcurrent2("2019/10/2","学习<java并发编程的艺术> 第四章到第五章5.3"),
	LearnConcurrent3("2019/10/5","学习<java并发编程的艺术> 第四章到第五章5.4"),
	LearnConcurrent4("2019/10/5-2019/10/6凌晨","学习<java并发编程的艺术>第五章"),
	LearnConcurrent5("2019/10/6","LeetCode157周赛"),
	LearnConcurrent6("2019/10/6","学习<java并发编程的艺术>第六章"),
	LearnConcurrent7("2019/10/7","学习<java并发编程的艺术>第七章"),
	LearnConcurrent8("2019/10/8","学习<java并发编程的艺术>第八章、第九章"),
	LearnConcurrent9("2019/10/9","学习<java并发编程的艺术>第十章、第十一章(完结)"),
	LearnConcurrent10("2019/10/12","日常leetcode"),
	LearnConcurrent11("2019/10/12","学习<图解HTTP>第一章到第四章"),
	LearnConcurrent12("2019/10/13","leetcode周赛158"),
	LearnConcurrent13("2019/10/13","mysql学习"),
	LearnConcurrent14("2019/10/14","投递简历复习知识点"),
	LearnConcurrent15("2019/10/15","投递简历复习spring"),
	LearnConcurrent16("2019/10/16-2019/10/21","面试+宣讲会/复习ing"),
	LearnConcurrent17("2019/10/19","崇汉面试：" +
			"jvm如何理解，gc，内存模型，如何检测oom，出现oom之后的办法" +
			"hashmap与treemap与linkedhashmap" +
			"springboot与springmvc的区别" +
			"springboot的启动过程" +
			"mysql的相关优化设置" +
			"如何理解redis" +
			"如何理解MQ" +
			"分布式并发环境下如何加锁（有一个redis锁）" +
			"设计登录时的详细过程" +
			"结果：30分钟左右，凉凉"),
	LearnConcurrent18("2019/10/25","威佩(LGD+VP)" +
			"了解的操作系统，如何实现的负载均衡，是否是Nginx" +
			"如何理解正向代理与反向代理" +
			"一个app或网页访问的整个网络的流程，包括各层协议（OSI7层）" +
			"http与https区别，访问https的整个过程" +
			"线程与进程的区别" +
			"线程之间是如何交互的，如何传递资源" +
			"(说到了等待通知模型)了解NIO的其他模型吗(好像说了Reactor)" +
			"了解协程吗，协程与线程的区别" +
			"Linux如何查看程序所占用的端口命令(想不起来)" +
			"java中的hashmap" +
			"Object类有哪些方法" +
			"equals与==区别" +
			"springmvc的访问过程" +
			"当传参错误时，发生错误在哪一层" +
			"过滤器与拦截器的理解，区别" +
			"当同时设置了过滤器与拦截器，哪一个先触发" +
			"为什么MySQL索引使用的是B+树" +
			"为何使用的B+树而不是多路搜索树（分块）" +
			"了解二叉树吗（回答了平衡二叉树:( ）" +
			"当平衡二叉树出现极端情况时，退化成链表，如何解决（AVL）" +
			"git中add与commit区别" +
			"git中的rebase与reset" +
			"reset的两种模式（head与sort）" +
			"算法题：丑数（leetcode264题原题！！！）" +
			"面试结果：一个小时左右，凉凉"),
	LearnConcurrent19("2019/10/26","赶电商作业，以及回顾凉经，复习丑数！！！"),
	LearnConcurrent20("2019/10/27","leetcode周赛(大失败！只做出一题！),格雷码+最后一题还是没有思路"),
	LearnConcurrent21("2019/10/28","日常leetcode刷题，127单词接龙未完成（DFS失败，BFS冲！），学习《深入理解java虚拟机》"),
	LearnConcurrent22("2019/10/31","日常leetcode刷题，75颜色分类（荷兰旗帜，三指针），131分割回文串（dfs+回溯）"),
	LearnConcurrent23("2019/11/02","利用WordPress搭建自己的博客网站，日常leetcode刷题，主要是格雷码(上周周赛，没做出来的循环码排列get√)"),
	LearnConcurrent24("2019/11/03","leetcode161周赛，题目好像有点简单，但是比赛中只做出一题，还有就是最后的裴蜀定理是什么鬼（更新LeetCode161周赛4道题）"),
	LearnConcurrent25("2019/11/04","日常leetcode以及pat一题(反转链表)"),
	LearnConcurrent26("2019/11/05","日常leetcode(39.组合综合-dfs、56.合并区间)"),
	LearnConcurrent27("2019/11/06","对公司接口熟悉,开发流程"),
	LearnConcurrent28("2019/11/07","日常leetcode(77.组合-dfs)"),
	LearnConcurrent29("2019/11/10","leetcode162周赛(第一题（签到题）第二题（遍历贪心)第三题（dfs-连同点的最大数量）"),
	LearnConcurrent30("2019/11/11","了解新的搜索算法BERT,由谷歌发布。已经用于生产环境。这种算法最大的特点，就是考虑了搜索词的语序。以前的算法只考虑每个词本身，搜索hello world与搜索world hello，结果是一样的。https://blog.google/products/search/search-language-understanding-bert/"),
	LearnConcurrent31("2019/11/12","日常leetcode(64.最小路径和)dfs-fail dp-success 找到leetcode资源,启动！"),
	LearnConcurrent32("2019/11/13","白天主要学习了docker，晚上看了leetcode讲解第一章与第二章！"),
	LearnConcurrent33("2019/11/18-2019/11/20","更新leetcode163周赛"),
	LearnConcurrent34("2019/11/23","搭建论坛模型，找后台模板"),
	LearnConcurrent35("2019/11/24","leetcode164周赛(完成23题 ps:现在已经要AK才能进前200了吗)"),
	LearnConcurrent36("2019/11/24晚","剑指offer第114页"),
	LearnConcurrent37("2019/11/25","完成大数据课程作业"),
	LearnConcurrent38("2019/11/26","学习springboot整合swagger2,学习一点shell知识"),
	LearnConcurrent39("2019/11/26晚","完成论坛注册功能,重写result类,增加log4j日志(前端问题:当一个方法调用多个ajax方法时，是否会发生调用顺序改变)"),
	LearnConcurrent40("2019/11/27","完成学校实训课内容"),
	LearnConcurrent41("2019/11/28","日常leetcode(31.下一个排列更大),Linux命令熟悉"),
	LearnConcurrent42("2019/11/30","日常leetcode+双周赛+部门团建"),
	// 双周赛(第一题使用库函数苟过,第二题分类讨论即可,第三题不知道为什么dfs没过。。。,第四题常规弃题) 排名(254/871)
	// 165周赛(第一题分类讨论,第二题数学题,第三题数据量较小暴力直接过,第四题有点难顶没做出来)ps本周手速相对不错 排名(158/1659)
	LearnConcurrent43("2019/12/01","昨天leetcode双周赛排名(254/871)和今天leetcode165周赛排名(158/1659)"),
	// 周六双周赛，第三题dfs程序粗心写错了。。。然而卡在了最后一个用例报T了
	LearnConcurrent44("2019/12/02","日常leetcode刷题,以及《leetcode视频》4-5"),
	LearnConcurrent45("2019/12/03","日常leetcode刷题,map表"),
	LearnConcurrent46("2019/12/04","日常leetcode刷题,以及《leetcode视频》第4章完"),
	LearnConcurrent47("2019/12/05","日常leetcode刷题,映射表相关"),
	// 小飞生日回学校，顺便讨论毕业旅行，荒废了两天
	LearnConcurrent48("2019/12/09","日常leetcode刷题+补刷上周leetcode166周赛"),
	LearnConcurrent49("2019/12/10","日常leetcode刷题+学习BFS"),
	LearnConcurrent50("2019/12/11","知网看论文，准备开题报告"),
	LearnConcurrent51("2019/12/12","日常leetcode刷题(栈相关)，以及《leetcode视频》6-3完"),
	LearnConcurrent52("2019/12/13","日常leetcode刷题(基础bfs)"),
	LearnConcurrent53("2019/12/14","leetcode15双周赛（275/797）题目比较简单但是还是只过了3题"),
	LearnConcurrent54("2019/12/15","leetcode167周赛（228/1534）题目感觉一般但是也只过了3题！（bfs一定要深入理解了！）"),
	LearnConcurrent55("2019/12/16","完成学校电子商务实习报告和毕业实习报告"),
	LearnConcurrent56("2019/12/17","日常leetcode刷题，树与dfs相关"),
	LearnConcurrent57("2019/12/18","webservice学习，soapUI学习使用"),
	LearnConcurrent58("2019/12/19","日常leetcode刷题，dfs，以及《leetcode视频》第七章完"),
	LearnConcurrent59("2019/12/22","leetcode周赛"),
	LearnConcurrent60("2019/12/25","完成文献综述摘要及引言"),
	LearnConcurrent61("2019/12/26","完成文献综述正文第一段"),
	LearnConcurrent62("2019/12/30","完成文献综述正文第二段第一节"),
	LearnConcurrent63("2020/01/01","完成文献综述,并找到外文翻译目标文章"),
	// 文献综述被导师说字数不够。。。
	LearnConcurrent64("2020/01/02","完成外文翻译摘要与引言"),
	// 翻译的时候不小心先翻译了最后一节。。。
	LearnConcurrent65("2020/01/03","完成外文翻译第2、4节，还差第三节和参考文献！"),
	// 第一题字符串处理，第二题循环遍历异或，可用前缀和的思想优化，第三题BFS，第四题放弃。。。
	LearnConcurrent66("2020/01/05","leetcode170周赛，排名287/1647"),
	LearnConcurrent67("2020/01/05晚","完成外文翻译，并提交文献综述与外文翻译到学校网站，搜集开题报告资料"),
	LearnConcurrent68("2020/01/06","完成开题报告 国内外现状"),
	LearnConcurrent69("2020/01/07","完成开题报告 第一部分《本课题国内外状况，说明选题依据和意义》完成 "),
	LearnConcurrent70("2020/01/08","完成开题报告 第二部分查阅资料"),
	// 中途被拉壮丁。。。
	LearnConcurrent71("2020/01/09","完成开题报告 第二部分50%"),
	LearnConcurrent72("2020/01/10","完成开题报告！"),
	// 第一次进前20名！
	LearnConcurrent73("2020/01/11","leetcode17双周赛大成功！（20/897）"),
	// 周赛失败。。。需要加强学习union-find和连通图
	LearnConcurrent74("2020/01/12","leetcode171周赛（457/1707）需要加强学习union-find和连通图"),
	LearnConcurrent75("2020/01/13","学习union-find"),
	LearnConcurrent76("2020/01/14-16","学习搜索引擎技术，Lucene与Solr"),
	LearnConcurrent77("2020/01/18","leetcode可能是乙亥年最有趣的比赛（140/1166）"),
	// 上班刷题，属实有点难
	LearnConcurrent78("2020/01/18","leetcode172周赛（510/1414）"),
	// 过年想要空下来写，属实难。。。
	LearnConcurrent79("2020/01/23","论坛分页功能基本完成"),
	LearnConcurrent80("2020/01/24","union-find学习巩固"),
	LearnConcurrent81("2020/01/27","补leetcode173周赛"),
	LearnConcurrent82("2020/01/29","填充数据，日常牛客刷题"),
	LearnConcurrent83("2020/01/30","补充分页footer，学习dp"),
	LearnConcurrent84("2020/01/31","补充分页功能,websocket"),
	LearnConcurrent85("2020/02/01","学习设计模式-单例-工厂"),
	LearnConcurrent86("2020/02/02","leetcode 174周赛"),
	LearnConcurrent87("2020/02/03","办理离职事项"),
	LearnConcurrent88("2020/02/04","学习设计模式-建造者模式-原型模式"),
	LearnConcurrent89("2020/02/05","完成爬虫工具(爬取51job招聘信息)");

	// 时间
	private final String learnTime;
	// 内容
	private final String context;


	private LearnLog(String learnTime,String context) {
		this.learnTime = learnTime;
		this.context = context;
	}
	public String getLearnTime() {
		return learnTime;
	}

	public String getContext() {
		return context;
	}



}
