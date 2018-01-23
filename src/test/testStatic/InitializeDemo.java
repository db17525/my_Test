package test.testStatic;

/*程序的确是在初始化静态变量public static Test t1=new Test("t1"); ，
只不过这个静态变量t1引用了一个Test实例，那只能是构造好这个实例（构造实例时就会初始化实例变量，构造块，构造函数），
然后赋值给静态变量t1后，才算初始化完t1；静态变量t2也是一样的。

另外，说一下：
对于类中的各代码的执行顺序：静态变量在类编译时全部初始化，非静态变量仅在实例化时才初始化
严格的说，静态变量是在类被加载到java虚拟机时，被初始化的，和编译没有关系。

简单来说静态变量和静态代码块优先执行,而在静态变量和静态代码块中的方法不会再去执行类中的静态变量和静态代码块,静态的东西都只加载一遍,
所以是先加载public static Test t1=new Test("t1"); 而在实例化过程中不会再去加载静态的变量和代码块,以此类推*/

 	/*
	 * static静态变量初始化，构造函数调用(每次都执行，初始化非静态变量，方法,目的就是防止构造函数调用非静态方法属性)
	 * 构造函数执行，main函数执行
	 * 
	 * 其实记住三点就行了~~~~搞的我都郁闷死了，给大家总结下：
	 * 1.先顺序加载static变量
	 * 2.无论何时只要执行构造函数，那么在执行构造函数方法体之前必须完成非静态变量，方法的加载(不限次数)
	 * 3.static执行完成后，classloader会执行main方法体，然后就正常执行呗~~~
	 */

/*java的加载顺序应该是这样的:
	main第一句之前载入类，只载入static修饰的语句。
	变量或者是方法块优先顺序按照代码排列上下决定。
	静态方法载入不执行。
	之后若是遇到调用构造器的,从上到下依次执行非静态变量定义、方法块。
	相对而言 ， 构造器优先级最低。*/
public class InitializeDemo {
	private static int k = 1;
	private static InitializeDemo t1 = new InitializeDemo("t1");
	private static InitializeDemo t2 = new InitializeDemo("t2");
	private static int i = print("i");
	private static int n = 99;
	static {
		print("静态块");
	}
	private int j = print("j");
	{
		print("构造块");
	}
	public InitializeDemo(String str) {
		System.out.println((k++) + ":" + str + "   i=" + i + "    n=" + n);
		++i;
		++n;
	}
	public static int print(String str) {
		System.out.println((k++) + ":" + str + "   i=" + i + "    n=" + n);
		++n;
		return ++i;
	}
	public static void main(String args[]) {
		new InitializeDemo("init");
	}
}

/*
public class Test {
public static int k=0;  ①main开始，先执行左边初始化Test t k=0	
public static Test t1=new Test("t1");  //②实例化new Test，跳到a=0，执行print("j")，输出1:j i=0 n=0  ③执行print("构造块")，输出2:构造块 i=1 n=1 ④执行public Test(t1) ，输出3:t1 i=2 n=2这句执行完毕。// public static Test t2=new Test("t2");//⑤同理可得输出4:j i=3 n=3  5:构造块    i=4 n=4  6:t2 i=5 n=5 
public static int i=print("i"); //⑥继续初始化Test t 执行这句输出7:i i=6 n=6  
public static int n=99;  //⑦执行这句，n=99
private int a=0;  
private int j = print("j");
{  
print("构造块");  
}  
static{  
print("静态块");  //⑧执行这句，输出8：静态块 i=7 n=99  Test t初始化完毕	}  
 public Test(String str){  
 System.out.println((++k)+":"+str+"   i="+i+"    n="+n);  
    ++i;++n;  
 }  
 public static int print(String str){  
 System.out.println((++k)+":"+str+"   i="+i+"    n="+n);  
 ++n;  
     return++i;  
 }  
 public static void main(String args[])  
 {  
//	 Test t=new Test("init");  //⑨实例化new Test("init")，调到执行a=0；print("j")输出9:j    i=8    n=100  ⑩执行｛构造块｝输出 10:构造块    i=9    n=101  
 接着执行构造函数Test("init")输出11:init    i=10    n=102  执行完毕。	 } 
}*/