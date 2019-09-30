package cn.Ideal.demo;

import testInterfaces.TestDefault;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSon {
	public static void main(String[] args) {
		TestDefault t=new TestDefault() {
			@Override
			public double c1(int a) {
				return 0;
			}
		};
		System.out.println(t.c1(123));
		System.out.println(t.sqrt(123));
		List<Integer> a=new ArrayList<>();
		a.add(1);
		a.add(4);
		a.add(3);
		a.add(7);
		a.add(5);
		Collections.sort(a,(o1, o2) -> o1.compareTo(o2));


	}
}
