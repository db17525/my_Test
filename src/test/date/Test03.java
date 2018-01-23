package test.date;

import java.math.BigDecimal;

public class Test03 {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(59.7);
		BigDecimal b = new BigDecimal(70.1);
		System.out.println(b.subtract(a));
		//System.out.println(temp.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)));
	}
}
