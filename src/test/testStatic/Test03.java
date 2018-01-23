package test.testStatic;

public class Test03{
	public static String name="张三";
	public static int age;
	static{
       age=20;
       System.out.println("初始化age");;
	}
	public static String address;
	static{
		address="北京市";
		age=34;;
	}
	public static void main(String[] args) {
                   System.out.println(name);
                   System.out.println(age);
                   System.out.println(address);
         }
	}