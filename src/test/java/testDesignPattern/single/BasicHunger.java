package testDesignPattern.single;

public class BasicHunger {
	// 在JVM加载时，这个类就被创建出来，所以保证了线程安全
	private static BasicHunger basicHunger = new BasicHunger();
	private BasicHunger(){ }

	public static BasicHunger getBasicHunger(){
		return basicHunger;
	}
}
