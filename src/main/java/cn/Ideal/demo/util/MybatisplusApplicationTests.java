package cn.Ideal.demo.util;


import jdk.nashorn.internal.runtime.logging.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Logger
public class MybatisplusApplicationTests implements Runnable{
	private static org.slf4j.Logger log=LoggerFactory.getLogger(MybatisplusApplicationTests.class);
	CyclicBarrier cyclicBarrier = new CyclicBarrier(2, this);
	Executor executor = Executors.newFixedThreadPool(2);
	public void sendmail() throws MessagingException {
		System.out.println("123");
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					EmailUtil.sendMail("usx16147220@163.com","wwwww","yyyyy");
					cyclicBarrier.await();
					System.out.println("一发送成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						EmailUtil.sendMail("840985277@qq.com","wtwt","sff");
						cyclicBarrier.await();
						System.out.println("二发送成功");


					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			System.out.println(456);
	}

	@Override
	public void run() {
		log.info("发送成功");
		System.out.println("发送成功");
	}
}
