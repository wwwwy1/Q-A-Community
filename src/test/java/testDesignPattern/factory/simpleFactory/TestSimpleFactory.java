package testDesignPattern.factory.simpleFactory;

public class TestSimpleFactory {
	public static void main(String[] args) {
		Shape circle = ShapeFactory.getShape("circle");
		circle.draw();
		Shape rectangle = ShapeFactory.getShape("rectangle");
		rectangle.draw();
		Shape square = ShapeFactory.getShape("square");
		square.draw();
		Circle circleReflex = (Circle) ShapeFactoryReflex.getClass(Circle.class);
		circleReflex.draw();
		Rectangle rectangleReflex = (Rectangle) ShapeFactoryReflex.getClass(Rectangle.class);
		rectangleReflex.draw();
		Square squareReflex = (Square) ShapeFactoryReflex.getClass(Square.class);
		squareReflex.draw();
	}
}
