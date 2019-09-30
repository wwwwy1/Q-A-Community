package cn.Ideal.demo;



import org.junit.experimental.max.MaxHistory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i = 0; i < n; i++) {
			int m=sc.nextInt();
			List<Integer> list=new ArrayList<>();
			for (int j = 0; j < m; j++) {
				list.add(sc.nextInt());
			}

			Collections.sort(list);
			int ans1=0;
			int ans2=0;
			int flag1=0;
			int flag2=0;
			if (m%2==0){
				for (int j = list.size()-1; j >=0 ; j--) {
					if (flag1<m/2 && ans1<=ans2){
						ans1+=list.get(j);
						flag1++;
					}else {
						if (flag2<m/2){
							ans2+=list.get(j);
							flag2++;
						}else {
							ans1+=list.get(j);
							flag1++;
						}
					}
				}
			}else {
				for (int j = list.size()-1; j >=0 ; j--) {
					if (flag1<(m/2+1) && ans1<=ans2){
						ans1+=list.get(j);
						flag1++;
					}else {
						if (flag2<(m/2+1)){
							ans2+=list.get(j);
							flag2++;
						}else {
							ans1+=list.get(j);
							flag1++;
						}
					}
				}
			}
			if (ans1<ans2)System.out.println(ans1+" "+ans2);
			else System.out.println(ans2+" "+ans1);
		}
	}

}


/*Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i = 0; i < n; i++) {
			int m=sc.nextInt();
			String a=sc.next();
			int c=a.indexOf("8");
			if (c==-1) System.out.println("NO");
			else if (m-c>=11) System.out.println("YES");
			else System.out.println("NO");
		}
* */
/*int m=sc.nextInt();
		int corp[]=new int[n];
		int count=0;
		for (int i = 0; i <n ; i++) {
			int t=sc.nextInt();
			count+=t;
			corp[i]=t;
		}
		int ans=0;
		int flag=0;
		while (count!=0){
			ans++;
			if (m>corp[flag]){
				m-=corp[flag];
				count-=corp[flag];
				corp[flag]=0;
			}
		}*/
// 2*3+4+2
// 3+5+4

/*
*
*算式转移
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
给出一个仅包含加减乘除四种运算符的算式(不含括号)，如1+2*3/4，在保持运算符顺序不变的情况下，现在你可以进行若干次如下操作：如果交换相邻的两个数，表达式值不变，那么你就可以交换这两个数。

现在你可以进行任意次操作，使得算式的数字序列字典序最小，然后输出结果，数字之间的字典序定义为若a<b则a的字典序小于b。

输入
第一行包含一个整数n，表示算式的长度，即包含n个数字和n-1个运算符。(1≤n≤100000)。

第二行包含一个含有n个非0整数和n-1个运算符的算式，整数与运算符用空格隔开，运算符包括“+，-，*，/”，整数的绝对值不超过1000。

输出
按要求输出字典序最小的表达式，数字与符号之间用空格隔开。


样例输入
6
3 + 2 + 1 + -4 * -5 + 1
样例输出
1 + 2 + 3 + -5 * -4 + 1
*
*
* */


/*
序列移除
* 时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 131072KB；其他语言 655360KB
题目描述：
现在有A，B两个序列，两个序列都是拥有n个元素，你有两种操作：

1. 从A序列中选择一个非空前缀，再从B序列中选择一个非空前缀，要求选择的这两个前缀的末尾元素相等。把这两个前缀移除，这个操作将花费Cost代价，但是这个操作可以使你得到一颗宝石。

2. 您可以重复第1步的操作；最终，您需要花费两个序列剩余元素数量之和大小的代价，移除两个序列中剩下的所有元素（这最后一步是没有宝石的），这时游戏结束。

要求总代价不超过Total，且序列中不得有剩余，那么最多可以获得多少宝石。

输入
输入第一行包含三个正整数n, Total , Cost ,  (1<=n<=5*10^4 , 1<=Total<=3*10^5 , 10^3<=Cost<=10^4)

接下来两行，每行n个数，表示A，B序列

输出
一个数表示最多可以获得的宝石数量


样例输入
5 10000 1000
1 5 4 2 3
5 4 3 2 1
样例输出
3

提示
按顺序消除末尾为5，4，3的前缀，最后再消除2，1可以获得3颗宝石。
*
*
*
* */
