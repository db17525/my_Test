package test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestDate {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.parse("2015-7-9"));
		
	}
}
//Tue Jul 09 00:00:00 CST 15
