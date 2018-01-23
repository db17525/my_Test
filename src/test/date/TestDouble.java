package test.date;

import java.math.BigDecimal;

public class TestDouble {
	public static void main(String[] args) {
		//String jfje = ".01";
		//System.out.println(jfje.indexOf(".") );
		BigDecimal a = new BigDecimal("1");
		BigDecimal b = new BigDecimal("12");
		BigDecimal c = new BigDecimal("111");
		System.out.println(c.divide(new BigDecimal("10000"), 2, BigDecimal.ROUND_HALF_UP));
	}
}
