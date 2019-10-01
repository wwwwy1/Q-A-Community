package testConcurrent;

import java.util.concurrent.TimeUnit;

public class Interrupted {
	public static void main(String[] args) throws InterruptedException {
		Thread sleepRunner = new Thread(new SleepRunner(),"sleep");
		Thread busyRunner = new Thread(new BusyRunner(),"busy");
		sleepRunner.setDaemon(true);
		busyRunner.setDaemon(true);
		sleepRunner.start();
		busyRunner.start();
		TimeUnit.SECONDS.sleep(5);
		//sleepRunner.interrupt();
		//busyRunner.interrupt();
		System.out.println("sleep :"+ sleepRunner.isInterrupted());
		System.out.println("busy :"+ busyRunner.isInterrupted());

	}

	static class SleepRunner implements Runnable{
		@Override
		public void run() {
			while (true){
				SleepUtils.second(10);
			}
		}
	}
	static class BusyRunner implements Runnable{
		@Override
		public void run() {
			while (true){

			}
		}
	}

}
