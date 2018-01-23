package test.dbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class dd {
	public static String getDriver() {
		return driver;
	}
	public static void setDriver(String driver) {
		dd.driver = driver;
	}
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		dd.url = url;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		dd.user = user;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		dd.password = password;
	}
	static private String driver = null;
	static private String url = null;
	static private String user = null;
	static private String password = null;
	public static void main(String[] args) {
		 Properties dbproperties = new Properties()  ;		
			try {

				FileInputStream fileInputStream = new FileInputStream(new File("db.properties"));

				dbproperties.load(fileInputStream) ;
				
				driver = dbproperties.getProperty("driver").toString();
                url = dbproperties.getProperty("url").toString();  
                user = dbproperties.getProperty("user").toString();  
                password = dbproperties.getProperty("password").toString();
                System.out.println(driver);

			} catch (FileNotFoundException e1) {

				e1.printStackTrace();

			}catch (IOException e) {

				e.printStackTrace();

			}

	}
}
