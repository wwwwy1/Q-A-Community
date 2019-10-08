package testConcurrent.num8Utils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExchangerTest {
	private static final Exchanger<String> exgr = new Exchanger<>();
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				String A="银行流水A";
				try {
					exgr.exchange(A);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				String B = "银行流水B";
				try {
					String A = exgr.exchange("");
					System.out.println("A和B数据是否一致" + A.equals(B) + ",A录入的是："+ A+"，B录入的是："+B);
				} catch (InterruptedException e) {
				}

			}
		});
		threadPool.shutdown();
	}
}
