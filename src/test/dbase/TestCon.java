package test.dbase;

import java.sql.Connection;

public class TestCon {
	public static void main(String[] args) {
		DBManagerService dbManagerService = new DBManagerService();
		Connection mysqlConnection = dbManagerService.getMyslqConnection();
		Connection oraConnection = dbManagerService.getMOracleConnection();
		dbManagerService.closeConnection(mysqlConnection);
		dbManagerService.closeConnection(oraConnection);
	}
}
