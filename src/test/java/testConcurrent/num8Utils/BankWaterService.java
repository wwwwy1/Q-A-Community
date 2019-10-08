package testConcurrent.num8Utils;

import java.util.Map;
import java.util.concurrent.*;

public class BankWaterService implements Runnable {
	// 创建4个屏障，处理完之后执行当前类的run方法
	private CyclicBarrier c = new CyclicBarrier(4,this);
	// 假设只有4个sheet，所以只启动4个线程
	private Executor executor = Executors.newFixedThreadPool(4);
	private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();
	private void count(){
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// 计算当前sheet的银流数据，计算代码省略
					sheetBankWaterCount.put(Thread.currentThread().getName(),1);
					try {
						c.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	@Override
	public void run() {
		Integer result=0;
		// 汇总每个sheet计算出的结果
		sheetBankWaterCount.forEach((k,v)->{
			System.out.println(k+":"+v);
			//result+=v;
		});
		for (Map.Entry<String, Integer> strings : sheetBankWaterCount.entrySet()) {
			result+=strings.getValue();
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		BankWaterService b= new BankWaterService();
		b.count();
	}
}
