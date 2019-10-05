package testConcurrent.num5Lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairANDUnfairTest {
	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unfairLock = new ReentrantLock2(false);
	@Test
	public void fair(){
		testLock(fairLock);
	}
	@Test
	public void unfair(){
		testLock(unfairLock);
	}
	private void testLock(Lock lock){
		List<Thread> list=new ArrayList<>();
		for (int i = 0; i <5 ; i++) {
			Thread thread=new Thread(new Job(lock),i+"1");
			list.add(thread);
		}
		for (int i = 0; i < 5; i++) {
			list.get(i).start();
		}

	}
	private static class Job extends Thread{
		private Lock lock;
		public Job(Lock lock){
			this.lock=lock;
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.print("current thread name:"+this.getName());
				printList(lock);
			}finally {
				lock.unlock();
			}
			lock.lock();
			try {
				System.out.print("current thread name:"+this.getName());
				printList(lock);
			}finally {
				lock.unlock();
			}

		}
		private void printList(Lock lock){
			ReentrantLock2 r=(ReentrantLock2) lock;
			Collection<Thread> queueThreads = r.getQueueThreads();
			System.out.print("the threadQueue: ");
			for (Thread t:queueThreads) {
				System.out.print(t.getName()+",");
			}
			System.out.println();
		}

		public static void main(String[] args) {
			FairANDUnfairTest a=new FairANDUnfairTest();
			a.unfair();
		}
	}




	public static class ReentrantLock2 extends ReentrantLock{
		public ReentrantLock2(boolean fair){
			super(fair);
		}
		public Collection<Thread> getQueueThreads(){
			List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
			Collections.reverse(arrayList);
			return arrayList;
		}
	}
}
