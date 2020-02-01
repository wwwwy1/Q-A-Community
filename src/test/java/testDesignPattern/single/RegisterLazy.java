package testDesignPattern.single;

public class RegisterLazy {
	// 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类
	// 从而实例化 instance（只有第一次使用这个单例的实例的时候才加载，同时不会有线程安全问题）。
	private static class RegisterLazyHolder{
		private static final RegisterLazy INSTANCE = new RegisterLazy();
	}
	private RegisterLazy(){}

	public static RegisterLazy getRegisterLazy(){
		return RegisterLazyHolder.INSTANCE;
	}
}
