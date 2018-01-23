package test.string;

public class test01 {
	public static void main(String[] args) {
		String a="12345";
		System.out.println(a.substring(1, 4));
		System.out.println(a.indexOf("6"));
		int i = 0;
		int b = i++;
		System.out.println(b+"---" + i);
	}
}
