package test.testStatic;

public class testStatic extends Fa{
	 
    static{
        System.out.println("son:静态代码块");
    }
    {
        System.out.println("son:构造代码块");
    }
    public testStatic(){
        System.out.println("son:构造函数块");
    }
    public testStatic(String s){
        //super(s);
        System.out.println("son:构造函数块"+s);
    }
    public static void main(String[] args) {
        testStatic te;
        System.out.println();
        Fa t=new testStatic("ai");
    }
 
}
