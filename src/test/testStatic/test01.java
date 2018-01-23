package test.testStatic;
/*private static TestStaticCode tsc = new TestStaticCode(); --1
    static{ --2
        System.out.println("4");
    }
同样的static级别，会按顺序执行，所以先执行 --1 后执行 --2
执行 --1的时候，应为 new TestStaticCode(); 会生成 TestStaticCode实例，所以会先调用 TestStaticCode的初始化块代码，然后再调用构造方法
private InstanceVariable iv = new InstanceVariable();
这条语句相当于2条语句
private InstanceVariable iv;
{iv = new InstanceVariable();} //这个就是初始化块代码，优先于构造方法
其他的就不用说明了，static{}静态块在类加载的时候被调用，{}非静态初始化块在实例对象被初始化构造方法被调用之前被调用
举个例子，LZ理解好这个例子就能理解你的代码了*/

public class test01 {
    public test01() {
        System.out.println("我是构造方法9");  //构造方法在对象实例化时非静态初始化块执行结束以后被执行
        System.out.println("我是构造方法10"); //同级别的安先后顺序执行
    }

    {System.out.println("我是非静态初始化块5");} //非静态初始化块在对象实例化时构造方法被调用之前被执行
    {System.out.println("我是非静态初始化块6");} //同级别的按先后顺序执行
    String str2 = "我是静态代码块7";
    {
        System.out.println(str2);
        str2 = "我是静态代码块8"; //这里重新赋值
        System.out.println(str2); //可见 String str2=xxx 相当于
                                  //String str; {str=xxx;}//在非静态初始化块赋值
                                  //非静态初始化块同级别按先后顺序执行
    }

    static {System.out.println("我是静态代码块1");} //静态块在类加载的时候被执行
    static {System.out.println("我是静态代码块2");} //同级别的按先后顺序执行
    static String str = "我是静态代码块3";
    static {
        System.out.println(str);
        str = "我是静态代码块4"; //这里重新赋值
        System.out.println(str); //可见 static String str=xxx 相当于
                                  //static String str; static {str=xxx;}//在静态块赋值
                              //静态块同级别按先后顺序执行
    }

    public static void main(String[] args) {
        new test01(); //LZ自己理解一下例子的执行顺序
    }
} 