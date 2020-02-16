package cn.Ideal.demo;

import java.util.ArrayList;
import java.util.List;

public class ProductOfNumbers {
	List<Integer> list;
	public ProductOfNumbers() {
		list = new ArrayList<>();
	}

	public void add(int num) {
		list.add(num);
	}

	public int getProduct(int k) {
		int ans = 0;
		if (k>0)ans=1;
		for (int i = list.size()-1; i > list.size()-1-k; i--) {
			ans*=list.get(i);
		}
		return ans;
	}
}
