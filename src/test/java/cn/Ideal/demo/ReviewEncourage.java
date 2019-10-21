package cn.Ideal.demo;

class ReviewEncourage {

	public ReviewEncourage(int n) { // 构造函数,n为中奖用户数

	}
    public class PrizePool {
        public void send(String input) {

            System.out.print(input);

        }

    }


	public void bonus(PrizePool prizePool) { // 仅能打印A，表示发放积分
		System.out.println('A');
	}

	public void coupon(PrizePool prizePool) { // 仅能打印B，表示发放优惠券
		System.out.println('B');
	}

	public void contribution(PrizePool prizePool) { // 仅能打印C，表示发放贡献值
		System.out.println('C');
	}

}
