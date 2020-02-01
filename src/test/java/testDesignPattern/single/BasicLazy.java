package testDesignPattern.single;

public class BasicLazy {
	// 在第一次调用时创建，但是并不能保证线程安全
	private static BasicLazy basicLazy;
	private BasicLazy(){}
	public static BasicLazy getBasicLazy(){
		if (basicLazy == null){
			// 当两个线程都进入到这边，会导致创建两次。 可以在这个get方法前面增加synchronized 来保证线程安全
			basicLazy = new BasicLazy();
		}
		return basicLazy;
	}
}
