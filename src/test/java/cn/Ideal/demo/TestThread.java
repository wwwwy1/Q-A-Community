package cn.Ideal.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TestThread {
	public static void main(String[] args) {
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println(threadInfo.getThreadId()+" : "+threadInfo.getThreadName());
		}
		HashMap<String,String> a=new HashMap<>();

	}
}
