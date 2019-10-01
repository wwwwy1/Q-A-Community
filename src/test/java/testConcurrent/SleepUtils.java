package testConcurrent;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
	public static final void second(long seconds){
		try {
			Thread.currentThread().sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
