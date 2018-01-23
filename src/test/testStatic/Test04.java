package test.testStatic;
/*1.执行顺序：静态代码>构造代码块>构造函数
2.静态代码块随类的声明而执行，而构造代码块和构造方法则在一个类实例化后执行，其中构造代码块又优先于构造方法执行
3.子类testStatic没有用super(s)时，默认调用fa的无参构造函数即输出：fa:构造函数块*/
public class Test04 {
	public static int k = 0;
	public static Test04 t1 = new Test04("t1");
	public static Test04 t2 = new Test04("t2");
	public static int i = print("i");
	public static int n = 99;
	private int a = 0;
	public int j = print("j");
	{
		print("构造块");
	}
	static {
		print("静态块");
	}

	public Test04(String str) {
		System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
		++i;
		++n;
	}

	public static int print(String str) {
		System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
		++n;
		return ++i;
	}

	public static void main(String args[]) {
		//Test04 t = new Test04("init");
	}
}
