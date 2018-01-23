package test.sup;

public class Checket extends Base{
	String name;
	 
	void value() {
	name = "Hefei";
	super.value();//不调用此方法时，super.name返回的是父类的成员变量的值null
	System.out.println(name);
	System.out.println(super.name);
	}
	 
	public static void main(String[] args) {
	Base c=new Base();
	c.value();
	}
}
