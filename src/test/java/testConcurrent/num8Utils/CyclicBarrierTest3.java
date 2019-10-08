package testConcurrent.num8Utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {
	static CyclicBarrier c = new CyclicBarrier(2);

	public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.interrupt();//如果线程中断则会产生异常
		try {
			c.await();
		}catch (Exception e){
			System.out.println(c.isBroken());
		}

	}

}
