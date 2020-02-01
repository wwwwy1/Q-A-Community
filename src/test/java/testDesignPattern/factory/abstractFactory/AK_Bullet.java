package testDesignPattern.factory.abstractFactory;

public class AK_Bullet implements Bullet {
	@Override
	public void load() {
		System.out.println("Load bullet with AK");
	}
}
