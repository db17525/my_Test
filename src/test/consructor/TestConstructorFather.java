package test.consructor;

public class TestConstructorFather {
	public int Avar;
	public TestConstructorFather() {
		System.out.println("AAA");
		doSomething();
	}
	public void doSomething() {
		Avar = 1111;
		System.out.println("A.doSomething()");
	}
}
