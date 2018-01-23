package test.testStatic;

/*类加载顺序：
* 	1.加载类的静态属性(非静态不管)
* 	这里加载的是:public static int k = 0;
* 	然后加载:public static StaticTest t1 = new StaticTest("t1");
* 	因为此处进行了类的实例化所以
* 	1.1加载类的非静态属性
* 	这里是:public int j = print("j");
* 	运行完这个方法接着
* 	1.2顺序加载类中的非static代码块(static暂时不加载)
* 	这里是:print("构造快");和print("静态块");
* 	运行完接着
* 	1.3加载类的构造方法
* 	这里是:public StaticTest(String str)
* 	运行完（一个静态属性的实例就完成了）
* 	2.继续加载类的静态属性
* 	这里加载的是:public static StaticTest t2 = new StaticTest("t2");
* 	2.1重复（1.1-1.3）
* 	3.继续加载类的静态属性
* 	这里加载的是:public static int i = print("i");
* 	运行完接着
* 	4.继续加载类的静态属性
* 	这里加载的是:public static int n = 99;
* 	不管你n原来是多少现在为99
* 	接着
* 	5.（如果有static代码块，在这里先加载，这个里面没有所以加载主函数）加载主函数
* 	这里加载的是:StaticTest t = new StaticTest("init");
* 	因为此处进行了类的实例化所以
* 	5.1
* 	重复1.1-1.3
* 	5.2
* 	因为public static int print(String str)这个方法返回++n
* 	所以n从99开始累加
* 	运行完OK了*/

public class StaticTest {
	
	public static int k = 0;
	public static StaticTest t1 = new StaticTest("t1");
	public static StaticTest t2 = new StaticTest("t2");
	public static int i = print("i");
	public static int n = 99;
	public int j = print("j");
	
	{
		print("构造快");
	}
	
	{
		print("静态块");
	}
	
	public StaticTest(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		++i;
	}
	
	public static int print(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++i;
		return ++n;
	}
	public static void main(String[] args) {
		StaticTest t = new StaticTest("init");
	}

}

