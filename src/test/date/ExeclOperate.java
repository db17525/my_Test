package test.date;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExeclOperate {
	
	public static void main(String[] args) throws Exception {
		ExeclOperate e=new ExeclOperate();
		e.getExcel();
		System.out.println("导入完成！");
	}
	/**
	 * 用于连接oracle数据库的方法
	 * 只需修改中的参数getConnection("url","用户名","密码");
	 */
	public Connection conn(){
		try {
		//第一步：加载JDBC驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//第二步：创建数据库连接
		Connection con =DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.156:1521:tianjiao", "E5AVPS", "E5AVPS");
		return con;
		}catch(ClassNotFoundException cnf){
		  System.out.println("driver not find:"+cnf);
		  return null;
		}catch(SQLException sqle){
		  System.out.println("can't connection db:"+sqle);
		  return null;
		}
		  catch (Exception e) {
		System.out.println("Failed to load JDBC/ODBC driver.");
		return null;
		}
	}
	/**
	 * “95509”咨询清单  的读取
	 * @throws Exception
	 */
	public void getExcel() throws Exception {
		// 创建对Excel工作簿文件的引用		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:\\1.xls"));		
		 // 创建对工作表的引用。
		 // 在Excel文档中，第一张工作表的缺省索引是0，
		 // 读取左上端单元
		 for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 循环sheet
				 if(workbook.getSheetAt(i).getPhysicalNumberOfRows()>0){
					 HSSFSheet childSheet = workbook.getSheetAt(i);
					 	for(int rowi=1;rowi<400000;rowi++){
					 		//System.out.println(childSheet.getRow(rowi).getCell(1).equals(""));
					 		if(childSheet.getRow(rowi).getCell(0).equals("") || childSheet.getRow(rowi).getCell(0)==null) break;
					 		String cell1=this.publicExcel(childSheet.getRow(rowi).getCell(0));
					 		if(cell1==null) break;
					 		//对于double类型的数据装换为string类型进行字符串截取 只取整数。
					 		cell1=cell1.substring(0, cell1.length()-2);
					 		String cell2=this.publicExcel(childSheet.getRow(rowi).getCell(1));
					 		String cell3=this.publicExcel(childSheet.getRow(rowi).getCell(2));
					 		String cell4=this.publicExcel(childSheet.getRow(rowi).getCell(3));
					 		String cell5=this.publicExcel(childSheet.getRow(rowi).getCell(4));
					 		String cell6=this.publicExcel(childSheet.getRow(rowi).getCell(5));
					 		cell6=cell6.substring(0, cell6.length()-2);
					 		//拼装插入数据库的sql	
					 		String insert="insert into w_95509 values('"+cell1+"','"+cell2+"','"+cell3+"','"+cell4+"','"+cell5+"','"+cell6+"')";
					 		System.out.println("SQL:"+insert);
					 		insert(insert);
					 		
					 	}	
				}
			}
	}

		/**
		 * execl数据格式的转换
		 * @param cell
		 * @return
		 */
		public String publicExcel( HSSFCell cell){
			String value = null;		
			      switch (cell.getCellType()) {
			      case HSSFCell.CELL_TYPE_NUMERIC:
			       value = "" + cell.getNumericCellValue();
			       break;
			      case HSSFCell.CELL_TYPE_STRING:
			       value = cell.getStringCellValue();
			       break;
			      case HSSFCell.CELL_TYPE_BLANK:
			       ;
			       break;
			      default: 
				}
			return value;
		}
		
		
		/**
		 * 插入数据 只需要传入插入sql即可
		 * 插入sql的样例:insert into t_department values('D004','金融部');
		 * @param insert 插入语句
		 * @return
		 * @throws SQLException 
		 */
		public int insert(String insert) throws SQLException{
			Connection conn = this.conn();
			int re = 0;
			try{
				conn.setAutoCommit(false);//事物开始				
				Statement sm = conn.createStatement();
				re = sm.executeUpdate(insert);
				if(re < 0){               //插入失败
					conn.rollback();      //回滚
					sm.close();
					conn.close();  
					return re;
				}
				conn.commit();            //插入正常
				sm.close();
				conn.close();  
				return re;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			conn.close();  
			return 0;
			
		} 

	}