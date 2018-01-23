package test.testStatic.fina;

public class Test1 {
	public static void main(String[] args) {
		// TODO 自动生成方法存根
	}

	public void f1() {
		System.out.println("f1");
	}

	// 无法被子类覆盖的方法
	public final void f2() {
		System.out.println("f2");
	}

	public void f3() {
		System.out.println("f3");
	}

	private void f4() {
		System.out.println("f4");
	}
}