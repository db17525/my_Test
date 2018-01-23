package test.dataimport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert_Data {

    public static Connection connection = null;
    PreparedStatement stmt=null;
    PreparedStatement stmt1=null;
    /**
     * 执行数据导入
     */
    public static void zx(){
    	String filepath="E:\\o2o平台交费记录导出\\导出o2o平台交费记录.txt";
    	Insert_Data daoru=new Insert_Data();
    	daoru.txtToDb(filepath);
    }
    /**
	 * filepath-txt:数据文件路径
	 */
	public void txtToDb(String file_path){
        String table_name="jfjl_t";
        String sqlInsert="";
        int count_row=0;//行数记录
        int hasnum=0;
		try
		{
			//链接数据库
            Connection ct=getConnection();
            if (ct!=null){
            	System.out.println("链接数据库成功！！！");
                //清空原本数据库
                /*String sqlClearTable = " DELETE FROM " + table_name;
                stmt=ct.prepareStatement(sqlClearTable);         
                stmt.executeUpdate();
                stmt.close();*/
            }else{
            	System.out.println("链接数据库失败！！！");
            }         
        	//读入文件
			File file = new File(file_path); 
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String read_data=buffer.readLine();
			while (read_data!=null){
				String[] str = null;
				str=read_data.split(";"); //据txt文件内的分号解析
				String bz = "";
				if (str!=null) {
					sqlInsert="select * from "+table_name+" where lsh = '"+str[0]+"'";
					stmt1=ct.prepareStatement(sqlInsert);
					hasnum = stmt1.executeUpdate();
					count_row++;
					if(hasnum==0){
						if(str.length>12){
							bz = str[12];
						}
						sqlInsert = "INSERT INTO "+table_name+"(lsh,qybh,yhbh,cnq,fylb,jfrq,jffs,jfje,znj,json,lrsj,czy,bz)"
						+" values "+"('"+str[0]+"',"+str[1]+","+str[2]+",'"+str[3]+"','"+str[4]+"',to_date('"+str[5]+"','yyyy-mm-dd hh24:mi:ss'),"
						+"'"+str[6]+"',"+str[7]+","+str[8]+",'"+str[9]+"',to_date('"+str[5]+"','yyyy-mm-dd hh24:mi:ss'),'"+str[11]+"','"+bz+"')";
						stmt1=ct.prepareStatement(sqlInsert);
						stmt1.executeUpdate();		
					}
				}
				read_data=buffer.readLine();
    		}		
			ct.commit();
			buffer.close();
			stmt1.close(); //注意及时关闭prepareStatement
			ct.close();
			System.out.println("插入数据完成！！！");
            }catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/*
	 * 获取数据库链接
	 */
	public static  Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{//配置JDBC连接
	      String url = "jdbc:oracle:thin:@192.168.7.146:1521:orcl";
	       Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	       connection = DriverManager.getConnection(url, "charge_mengtai", "charge_mengtai");
	       return connection;
	   }
    
}