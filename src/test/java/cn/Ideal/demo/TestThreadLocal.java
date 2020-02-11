package cn.Ideal.demo;

import java.util.*;
import java.util.stream.Collectors;

public class TestThreadLocal {


	/*public void b(){
		this.s();
	}*/

	public static void main(String[] args) throws InterruptedException {
		/*int cou=0;
		for (int i = 0; i <=100; i++) {
			cou=cou++;
		}
		AtomicInteger a=new AtomicInteger();
		Map<String,String> s=new HashMap<>();
		//String s="123";
		//s+="223";
		TestThreadLocal testThreadLocal = new TestThreadLocal();
		System.out.println(cou);*/
		List<Integer>  list =new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(1);
		Set<Integer> collect = list.stream().collect(Collectors.toSet());
		System.out.println(collect);

	}
}

/*能够熟练掌握java编程，具有良好的编程习惯与设计思想。
熟悉常见的开源框架，如：spring boot，spring mvc，mybatis，mybatis-plus。
了解前端与后端的交互方式，并且熟悉一定的前端技术，如：js，jQuery，ajax，vue。
掌握常见的关系型数据库，如：mysql、SQLserver。以及非关系型数据库Redis。
能够使用git/maven工具，有一定的逻辑分析、数据分析、问题排查能力。
掌握常见的开发工具。如：Idea，eclipse、Visual Studio等
具有团队协作精神，能够承受工作压力，有较高的执行力。*/
