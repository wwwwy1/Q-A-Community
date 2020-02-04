package testDesignPattern.builder;

public class MealB extends MealBuilder {
	@Override
	public void buildFood() {
		meal.setFood("鸡翅");
	}

	@Override
	public void buildDrink() {
		meal.setDrink("雪碧");
	}
}
