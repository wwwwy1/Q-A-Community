package testDesignPattern.builder;

public class MealA extends MealBuilder {

	@Override
	public void buildFood() {
		meal.setFood("薯条");
	}

	@Override
	public void buildDrink() {
		meal.setDrink("可乐");
	}
}
