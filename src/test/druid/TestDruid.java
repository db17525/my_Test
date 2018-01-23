package test.druid;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;

public class TestDruid {

	private static void executeUpdateBySQL(String sql) throws SQLException {
		DbPoolConnection dbp = DbPoolConnection.getInstance();
		DruidPooledConnection con = dbp.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeQuery(sql);
		ps.close();
		con.close();
		dbp = null;
	}
	
	public static void main(String[] args) {
		try {
			executeUpdateBySQL("select * from all_user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
