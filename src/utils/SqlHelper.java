package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SqlHelper {
	
	public static Connection getConn(){
		Properties prop = null;
		Connection conn = null;
		InputStream is = null;
		prop = new Properties();
		try {
			is = SqlHelper.class.getResourceAsStream("/db.properties");
			prop.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String passwd = prop.getProperty("password");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Oracle Connected Success!---"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		} catch (SQLException e) {
			System.err.println("sql exception:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Driver Class not found " + e.getMessage());
		}
		return conn;
	}
	
	//关闭ResultSet对象
		public static void close(ResultSet rs){
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//关闭PreparedStatement对象
		public static void close(PreparedStatement pstmt){
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//关闭Connection对象
		public static void close(Connection conn){
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("222");
					e.printStackTrace();
				}
			}
		}
		
		public static void commit(Connection conn){
			if(conn!=null){
				try {
					conn.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public static void rollback(Connection conn){
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
