package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5 {
	
	
	public static void main(String[] args) {
		 Format format = new SimpleDateFormat("yyyy-MM-dd");
		 String s = format.format(new Date());
		 Date d=new Date();   
		 String s1 = format.format(new Date(d.getTime() - 20 * 24 * 60 * 60 * 1000));
		 System.out.println(s);
		 System.out.println(s1);
	}
	/**
	 * 使用MD5加密
	 * @param password
	 * @return
	 */
	public static String MD5Encrypt(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
//			System.err.println(pwd);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
}
