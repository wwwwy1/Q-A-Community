package cn.Ideal.demo;

import testDesignPattern.single.EnumHunger;

import java.util.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		EnumHunger a = EnumHunger.INSTANCE;
		a.doSomeThing();
	}
}




/*
* 美团点评 2020校招 后台方向在线考试
编程题|20.0分1/2
美团骑手包裹区间分组
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 8192KB；其他语言 532480KB
题目描述：
2110年美团外卖火星第3000号配送站点有26名骑手，分别以大写字母A-Z命名，因此可以称呼这些骑手为黄家骑士特工A，黄家骑士特工B…黄家骑士特工Z，某美团黑珍珠餐厅的外卖流水线上会顺序产出一组包裹，美团配送调度引擎已经将包裹分配到骑手，并在包裹上粘贴好骑手名称，如RETTEBTAE代表一组流水线包裹共9个，同时分配给了名字为A B E R T的5名骑手。请在不打乱流水线产出顺序的情况下，把这组包裹划分为尽可能多的片段，同一个骑手只会出现在其中的一个片段，返回一个表示每个包裹片段的长度的列表。

输入
输入数据只有一行，为一个字符串(不包含引号)，长度不超过1000，只包含大写字母'A'到'Z'，字符之间无空格。

输出
输出每个分割成片段的包裹组的长度，每个长度之间通过空格隔开


样例输入
MPMPCPMCMDEFEGDEHINHKLIN
样例输出
9 7 8

提示
划分结果为 MPMPCPMCM, DEFEGDE, HINHKLIN。
每个骑手最多出现在一个片段中。
像 MPMPCPMCMDEFEGDE, HINHKLIN 的划分是错误的，因为划分的片段数较少。
*
* public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str = sc.next();
		int dict[] = new int[26];
		for (int i = 0; i < str.length(); i++) {
			dict[str.charAt(i)-'A']=i;
		}
		int flag=0;
		List<Integer> ans =new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			flag=Math.max(flag,dict[str.charAt(i)-'A']);
			if (flag==i)ans.add(flag);
		}
		flag = ans.get(0);
		System.out.print(flag+1);
		for (int i = 1; i < ans.size();i++) {
			System.out.print(" "+(ans.get(i)-flag));
			flag=ans.get(i);
		}
	}
*
*
*
*
*
*评价运营活动激励发放
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
为了激励更多的用户发表点评，大众点评在近期组织了一次促评排行榜的活动。在活动期间, 写评价数排名靠前的用户将获得对应的奖励，奖励分为积分、优惠券、贡献值三类。为了让活动更有趣味性，不同排名的用户将获得不同类型的激励。同时，为了保证激励发放效率，这三类激励会并行发放。

我们把问题简单描述一下，假定有一个激励发放的类，如下所示：

class ReviewEncourage {

  public ReviewEncourage(int n) { ... }      // 构造函数,n为中奖用户数


    PrizePool类仅有一个send方法，实现如下：

    public class PrizePool {

        public void send(String input) {

            System.out.print(input);

        }

    }



	public void bonus(PrizePool prizePool) { ... }  // 仅能打印A，表示发放积分

	public void coupon(PrizePool prizePool) { ... }  // 仅能打印B，表示发放优惠券

	public void contribution(PrizePool prizePool) { ... }  // 仅能打印C，表示发放贡献值

}

同一个ReviewEncourage实例将会传递给三个不同的线程用于激励发放：

		1.积分发放线程将会调用bonus方法发放积分

		2.优惠券发放线程将会调用coupon方法发放优惠券

		3.贡献值发放线程将会调用contribution方法发放贡献值

		要求依次对不同排位的用户发放不同类型的奖励，其中排位为奇数的用户发放积分，排位为偶数的用户交替发放优惠券和贡献值。

		例如一共5个中奖用户，要求对第一个用户发放积分，第二个用户发放优惠券，第三个用户发放积分，第四个用户发放贡献值，第五个用户发放积分，即输出ABACA

		要求补全以上代码，输出指定字符串序列

		输入
		获奖用户数n，n大于0，小于等于100

		输出
		由A、B、C组成的字符串，长度为n，奇数位为A，偶数位交替为B和C

		提示: 三个激励发放线程异步执行，不保证执行顺序


		样例输入
		1
		样例输出
		A

		提示
		输入样例2
		4
		输出样例2
		ABAC

		输入样例3
		5
		输出样例3
		ABACA
		输入样例4
		10
		输出样例4
		ABACABACAB
* */


































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
