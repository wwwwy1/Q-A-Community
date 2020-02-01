package testDesignPattern.factory.factoryMethod;

import testDesignPattern.factory.simpleFactory.Circle;
import testDesignPattern.factory.simpleFactory.Shape;

public class TestFactoryMethod {
	public static void main(String[] args) {
		Factory circleFactory = new CircleFactory();
		Shape circle = circleFactory.getShape();
		circle.draw();
	}
}
