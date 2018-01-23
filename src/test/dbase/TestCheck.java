package test.dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCheck {
	public static void main(String[] args) throws SQLException {
		DBManagerService dbManagerService = new DBManagerService();
		Connection mysqlConnection = dbManagerService.getMyslqConnection();
		Connection oraConnection = dbManagerService.getMOracleConnection();
		shareDate(mysqlConnection,oraConnection);
		dbManagerService.closeConnection(mysqlConnection);
		dbManagerService.closeConnection(oraConnection);
	}
	
public static void shareDate(Connection conn_mysql,Connection conn_ora) throws SQLException{		
		
		PreparedStatement ps_sql_read = null;
		PreparedStatement ps_pg_write = null;
		PreparedStatement ps_sql_write = null;
		
		ResultSet rs_pg_write = null;
		ResultSet rs_sql_read = null;
		ResultSet rs_sql_write = null;
		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String maxLsh = "";
		
		try {
			//ps_pg_write=conn_ora.prepareStatement("insert into jfjl_t (LSH, QYBH, YHBH, CNQ, FYLB, JFRQ, JFFS, JFJE, ZNJ, JSON, LRSJ, CZY, BZ) values(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			ps_pg_write=conn_ora.prepareStatement("insert into jfjl2_t (LSH, QYBH, YHBH, CNQ, JFRQ, JFJE, ZNJ, LRSJ, BZ, CZY) values(?,?,?,?,?,?,?,?,?,?) ");
			//获取oracle jfjl_t中最大流水号
			ps_sql_read=conn_ora.prepareStatement("SELECT MAX(LSH) lsh FROM jfjl2_t WHERE JFFS='O2O平台'");
			rs1 = ps_sql_read.executeQuery();
			while(rs1.next()){
				maxLsh = rs1.getString("lsh");
			}
			if(maxLsh==null){
				maxLsh = "1";
			}
			//获取mysql中新交费记录
			ps_sql_write=conn_mysql.prepareStatement("select * from jfjl_t where lsh > " + maxLsh);
			rs2 = ps_sql_write.executeQuery();
			while(rs2.next()){
				Object lsh=rs2.getObject("lsh");
				Object qybh =rs2.getObject("qybh");
				Object yhbh=rs2.getObject("yhbh");
				Object cnq=rs2.getObject("cnq");
				Object jfrq=rs2.getObject("jfrq");
				Object jfje=rs2.getObject("jfje");
				Object znj=rs2.getObject("znj");
				Object lrsj=rs2.getObject("lrsj");
				Object bz=rs2.getObject("bz");
				Object fylb=rs2.getObject("fylb");
				Object jffs=rs2.getObject("jffs");
				//Object czy=rs2.getObject("czy");
				String czy=rs2.getString("czy");
				Object json=rs2.getObject("json");
				
				ps_pg_write.setObject(1,lsh);
				ps_pg_write.setObject(2,qybh);
				ps_pg_write.setObject(3,yhbh);
				ps_pg_write.setObject(4,cnq);
				ps_pg_write.setObject(5,jfrq);
				ps_pg_write.setObject(6,jfje);
				ps_pg_write.setObject(7,znj);
				ps_pg_write.setObject(8,lrsj);
				ps_pg_write.setObject(9,bz);
				ps_pg_write.setString(10,czy);
				//ps_pg_write.setObject(11,jffs);
				//ps_pg_write.setObject(12,czy);
				//ps_pg_write.setObject(13,json);
				
				ps_pg_write.execute();
			}
		} finally{
			try{
				if(rs_pg_write!=null){
					rs_pg_write.close();
					rs_pg_write=null;
				}
				if(rs_sql_read!=null){
					rs_sql_read.close();
					rs_sql_read=null;
				}
				if(rs_sql_write!=null){
					rs_sql_write.close();
					rs_sql_write=null;
				}
				if(ps_pg_write!=null){
					ps_pg_write.close();
					ps_pg_write=null;
				}
				if(ps_sql_read!=null){
					ps_sql_read.close();
					ps_sql_read=null;
				}
				if(ps_sql_write!=null){
					ps_sql_write.close();
					ps_sql_write=null;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
