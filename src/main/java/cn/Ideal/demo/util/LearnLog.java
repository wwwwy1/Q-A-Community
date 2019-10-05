package cn.Ideal.demo.util;

public enum  LearnLog {
	/*
	 * 学习日志：10月1日开始
	 * 日期，内容
	 * */
	LearnConcurrent1("2019/10/1","学习<java并发编程的艺术> 第一章第二章第四章1/2"),
	LearnConcurrent2("2019/10/2","学习<java并发编程的艺术> 第四章到第五章5.3"),
	LearnConcurrent3("2019/10/5","学习<java并发编程的艺术> 第四章到第五章5.4"),
	LearnConcurrent4("2019/10/5-2019/10/6凌晨","学习<java并发编程的艺术>第五章");

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
