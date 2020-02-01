package testDesignPattern.single;

public class DoubleCheckedLazy {
	// 利用synchronized 来保证doubleCheckedLazy只被创建一次，
	// 并且利用volatile保证doubleCheckedLazy的数据可见性
	private static volatile DoubleCheckedLazy doubleCheckedLazy;
	private DoubleCheckedLazy(){}

	public DoubleCheckedLazy getDoubleCheckedLazy(){
		if (doubleCheckedLazy == null){
			synchronized (DoubleCheckedLazy.class){
				doubleCheckedLazy = new DoubleCheckedLazy();
			}
		}
		return doubleCheckedLazy;
	}
}
