package testConcurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	private AtomicInteger atomicI = new AtomicInteger(0);
	private  int i=0;
	private static volatile String a="1";

	public static void main(String[] args) {
		final Counter cas = new Counter();
		List<Thread> ts = new ArrayList<>(600);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
						cas.safeCount();
						cas.count();
					}
				}
			});
			ts.add(t);
		}
		for (Thread t: ts) {
			t.start();
		}
		//等待所有线程执行完成
		for (Thread t:ts) {
			try {
				t.join();
				System.out.println(cas.atomicI);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("---------");
		System.out.println(cas.i);
		System.out.println(cas.atomicI.get());
		System.out.println(System.currentTimeMillis()-start);
	}

	/*
	* 使用CAS实现线程安全计数器
	* */
	private void safeCount(){
		for (;;){
			int i=atomicI.get();
			boolean suc = atomicI.compareAndSet(i,++i);
			if (suc)break;
		}
	}
	private void count(){
		i++;
	}

}
