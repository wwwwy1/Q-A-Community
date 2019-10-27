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
	LearnConcurrent20("2019/10/27","leetcode周赛(大失败！只做出一题！),格雷码+最后一题还是没有思路");






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
