package test.consructor;

public class Student extends People{
    private int sno;
    public Student(){
    	super();
        System.out.println("初始化Student的实例变量--无参构造器");
    }
    public Student(int sno){
        System.out.println("初始化Student的实例变量--有参构造器");
        this.sno = sno;
    }
    
    public Student(String name, int age, int sno){
        //super(name, age);
        System.out.println("???");
        this.sno = sno;
    }
    public static void main(String[] args){
        Student s1 = new Student();
        Student s2 = new Student(1);
        Student s3 = new Student("huhu", 20, 2);
    }
}
