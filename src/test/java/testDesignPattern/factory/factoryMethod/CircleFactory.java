package testDesignPattern.factory.factoryMethod;

import testDesignPattern.factory.simpleFactory.Circle;
import testDesignPattern.factory.simpleFactory.Shape;

public class CircleFactory implements Factory {
	@Override
	public Shape getShape() {
		return new Circle();
	}
}
