package cn.Ideal.demo;

import java.util.ArrayList;
import java.util.List;

public class CustomStack {
	List<Integer> list = new ArrayList<>();
	int max;
	public CustomStack(int maxSize) {
		max = maxSize;
	}

	public void push(int x) {
		if (list.size() >= max) return;
		list.add(x);
	}

	public int pop() {
		if (list.size()==0) return -1;
		int t = list.get(list.size()-1);
		list.remove(list.size()-1);
		return t;
	}

	public void increment(int k, int val) {
		k = Math.min(k,list.size());
		for (int i = 0; i < k; i++) {
			list.set(i,list.get(i)+val);
		}
	}
}
