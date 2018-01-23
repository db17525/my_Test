package test.testStatic;

public class Test02 {
	int method() {return n; }
    int m = method();//0
    int n = 1;
    
    public static void main(String[] args) {
		Test02 test02 = new Test02();
		System.out.println(test02.m);
		System.out.println(test02.n);
	}
}
