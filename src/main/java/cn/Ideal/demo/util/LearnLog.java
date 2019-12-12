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
	LearnConcurrent51("2019/12/12","日常leetcode刷题(栈相关)，以及《leetcode视频》6-3完");








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
