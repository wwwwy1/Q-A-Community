package testConcurrent;

import sun.java2d.loops.GraphicsPrimitive;

public class Daemon {
	public static void main(String[] args) {
		//Thread.currentThread().setDaemon(true);
		Thread t1 = new Thread(new DaemonRunner() , "daemon");
		System.out.println("123");
		t1.setDaemon(true);
		t1.start();
	}
	static class DaemonRunner implements Runnable{

		@Override
		public void run() {
			try {
				SleepUtils.second(10);
			}finally {
				System.out.println("123");
			}
		}
	}
}
