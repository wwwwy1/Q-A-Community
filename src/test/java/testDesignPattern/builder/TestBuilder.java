package testDesignPattern.builder;

public class TestBuilder {
	public static void main(String[] args) {
		MealA a = new MealA();
		KFCWaiter waiter = new KFCWaiter(a);
		Meal mealA = waiter.construct();
		System.out.println(mealA);
	}

}
