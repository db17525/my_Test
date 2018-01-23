package test.testStatic.SubClass;

public class SuperClass {
	private int number;

	public SuperClass() {
		this.number = 0;
	}
	
	public SuperClass(int number) {
		this.number = number;
	}
	
	public int getNumber(){
		number++;
		return number;
	}
}
