package testDesignPattern.factory.factoryMethod;

import testDesignPattern.factory.simpleFactory.Rectangle;
import testDesignPattern.factory.simpleFactory.Shape;

public class RectangleFactory implements Factory {
	@Override
	public Shape getShape() {
		return new Rectangle();
	}
}
