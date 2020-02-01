package testDesignPattern.single;

public enum  EnumHunger {
	// 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。
	// 它更简洁，自动支持序列化机制，绝对防止多次实例化
	// 同时这种方式也是《Effective Java 》以及《Java与模式》的作者推荐的方式。
	INSTANCE;
	public void doSomeThing(){
		System.out.println("do something");
	}
}
