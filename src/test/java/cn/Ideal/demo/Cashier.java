package cn.Ideal.demo;

import java.util.HashMap;
import java.util.Map;

class Cashier {
	int num = 0;
	int discountNum = 0;
	Map<Integer,Integer> pri;
	int flag = 0;
	public Cashier(int n, int discount, int[] products, int[] prices) {
		num = n;
		discountNum = discount;
		pri = new HashMap<>();
		for (int i = 0; i < products.length; i++) {
			pri.put(products[i],prices[i]);
		}
	}

	public double getBill(int[] product, int[] amount) {
		flag++;
		double a = 0;
		for(int i = 0;i<product.length;i++){
			int k = pri.get(product[i]);
			a+=k*amount[i];
		}
		if(flag%num==0){
			a = a - (discountNum * a) / 100;
		}
		return a;
	}
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */