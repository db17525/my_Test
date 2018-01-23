package test.testStatic;
//静态代码块-->非静态代码块-->构造函数-->级别相同情况下按先后顺序执行
public class TestStaticCode {
    //private static TestStaticCode tsc = new TestStaticCode();//new 对象直接调用构造函数
    static{
        System.out.println("4");
    }
    private InstanceVariable iv = new InstanceVariable();
     
    private TestStaticCode(){
        System.out.println("3");
    }
 
    public static void main(String[] args){ 
    	//new TestStaticCode();//main方法有new一个对象，2，3是new对象的结果
    }
}
