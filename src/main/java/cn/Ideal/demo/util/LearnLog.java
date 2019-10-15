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
	LearnConcurrent15("2019/10/15","投递简历复习spring");





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
