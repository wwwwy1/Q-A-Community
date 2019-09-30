package cn.Ideal.demo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestSort {
	static int arr[]={3,1,2,5,4,9,6,8,7};
	public static void maopao(){
		int n=arr.length;
		for (int i = n-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
	}
	public static void xuanze(){
		int n=arr.length;
		for (int i = n-1; i > 0; i--) {
			int max=arr[0];
			int pos=0;
			for (int j = 0; j <= i; j++) {
				if (arr[j]>max){
					max=arr[j];
					pos=j;
				}
			}
			if (pos!=i){
				int temp=arr[pos];
				arr[pos]=arr[i];
				arr[i]=temp;
			}
		}
	}
	public static void charu(){
		int n=arr.length;
		for (int i = 1; i < n; i++) {
			int j=i;
			int k=arr[i];
			while (arr[j-1]>k){
				arr[j]=arr[j-1];
				j--;
				if (j==0)break;
			}
			arr[j]=k;
		}
	}

	public static void main(String[] args) {
		charu();
		System.out.println(Arrays.toString(arr));
	}
}
