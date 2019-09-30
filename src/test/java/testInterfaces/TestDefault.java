package testInterfaces;

public interface TestDefault {
	double c1(int a);
	default double sqrt(int a){
		return Math.sqrt(a);
	}
}
