package test.testStatic.SubClass;

public class SubClass extends SuperClass {

	private int number;

	public SubClass(int number) {
		super(number);
	}
	
	public int getNumber(){
		number++;
		return number;
	}

	public static void main(String[] args) {
		SuperClass s = new SubClass(20);
		System.out.println(s.getNumber());
		SuperClass s1 = new SubClass1(20);
		System.out.println(s1.getNumber());
		SuperClass s2 = new SubClass2(20);
		System.out.println(s2.getNumber());
	}

}
