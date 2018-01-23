package test.testStatic;
/*分析:当执行new Child()时，它首先去看父类里面有没有静态代码块，如果有，它先去执行父类里面静态代码块里面的内容，
当父类的静态代码块里面的内容执行完毕之后，接着去执行子类(自己这个类)里面的静态代码块，当子类的静态代码块执行完毕之后，
它接着又去看父类有没有非静态代码块，如果有就执行父类的非静态代码块，父类的非静态代码块执行完毕，接着执行父类的构造方法；
父类的构造方法执行完毕之后，它接着去看子类有没有非静态代码块，如果有就执行子类的非静态代码块。子类的非静态代码块执行完毕
再去执行子类的构造方法，这个就是一个对象的初始化顺序。

总结: 对象的初始化顺序:首先执行父类静态的内容，父类静态的内容执行完毕后，接着去执行子类的静态的内容，当子类的静态内容执行完毕之后，
再去看父类有没有非静态代码块，如果有就执行父类的非静态代码块，父类的非静态代码块执行完毕，接着执行父类的构造方法；父类的构造方法
执行完毕之后，它接着去看子类有没有非静态代码块，如果有就执行子类的非静态代码块。子类的非静态代码块执行完毕再去执行子类的构造方法。
总之一句话，静态代码块内容先执行，接着执行父类非静态代码块和构造方法，然后执行子类非静态代码块和构造方法。

注意:子类的构造方法，不管这个构造方法带不带参数，默认的它都会先去寻找父类的不带参数的构造方法。如果父类没有不带参数的构造方法，
那么子类必须用supper关键子来调用父类带参数的构造方法，否则编译不能通过。*/

//静态代码块:在java中使用static关键字声明的代码块。静态块用于初始化类，为类的属性初始化。每个静态代码块只会执行一次。由于JVM在加载类时会执行静态代码块，所以静态代码块先于主方法执行。
//如果类中包含多个静态代码块，那么将按照"先定义的代码先执行，后定义的代码后执行"。
//注意：1 静态代码块不能存在于任何方法体内。2 静态代码块不能直接访问静态实例变量和实例方法，需要通过类的实例对象来访问。
class Parent {
	static String name = "hello";
	{
		System.out.println("parent block");
	}
	static {
		System.out.println("parent static block");
	}

	public Parent() {
		System.out.println("parent constructor");
	}
}

class Child extends Parent {
	static String childName = "hello";
	{
		System.out.println("child block");
	}
	static {
		System.out.println("child static block");
	}

	public Child() {
		System.out.println("child constructor");
	}
}

public class StaticIniBlockOrderTest {
	public static void main(String[] args) {
		new Child();
	}
}
