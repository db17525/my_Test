package test.dbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManagerService {
 private Properties prop;
 private Connection conn;
 
 public Connection getMyslqConnection(){
  prop = new Properties();
  try {
   prop.load(new FileInputStream("db.properties"));
  } catch (IOException e1) {
   e1.printStackTrace();
  }
  
  String driver = prop.getProperty("mysqldriver");
  String url = prop.getProperty("mysqlurl");
  String user = prop.getProperty("mysqluser");
  String passwd = prop.getProperty("mysqlpassword");
  try {
   Class.forName(driver);
   conn = DriverManager.getConnection(url, user, passwd);
   System.out.println("Mysql Connected Success!");
  } catch (SQLException e) {
   System.err.println("sql exception:" + e.getMessage());
  } catch (ClassNotFoundException e) {
   System.err.println("Driver Class not found " + e.getMessage());
  }
  return conn;
 }
 
 public Connection getMOracleConnection(){
  prop = new Properties();
  try {
   prop.load(new FileInputStream("db.properties"));
  } catch (IOException e1) {
   e1.printStackTrace();
  }
  String driver = prop.getProperty("oracledriver");
  String url = prop.getProperty("oracleurl");
  String user = prop.getProperty("oracleuser");
  String passwd = prop.getProperty("oraclepassword");
  try {
   Class.forName(driver);
   conn = DriverManager.getConnection(url, user, passwd);
   System.out.println("Oracle Connected Success!");
  } catch (SQLException e) {
   System.err.println("sql exception:" + e.getMessage());
  } catch (ClassNotFoundException e) {
   System.err.println("Driver Class not found " + e.getMessage());
  }
  return conn;
 }
 
  /**
   * 关闭数据库连接
   *
  * @param connect
   */  
 public void closeConnection(Connection conn) {
  try {
   if (conn != null) {
    /** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
    if (!conn.isClosed()) {
    	System.out.println("连接已关闭");
     conn.close();
    }
   }
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }
}