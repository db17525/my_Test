package test.testStatic;

class Fa{
    static{
        System.out.println("fa:静态代码块");
    }
    {
        System.out.println("fa:构造代码块");
    }
    public Fa(){
        System.out.println("fa:构造函数块");
    }
    public Fa(String s){
        System.out.println("fa:构造函数块"+s);
    }
}
