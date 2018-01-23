package test.testStatic;

public class InstanceVariable {
    static{
        System.out.println("1");
    }
    public InstanceVariable(){
        System.out.println("2");
    }
}
