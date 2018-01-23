package test.date;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class loadEXCEL{
  
      public static Connection con=null;
  
      public static void build(Connection conn) throws BiffException, IOException{
              String filepath="d://te.xlsx";
              File excelfile = new File(filepath);
              Workbook book = null;
              CallableStatement st = null;
              int rownum;
              String name = null;
              String sex = null;
              try{
                    book = Workbook.getWorkbook(excelfile);
                    Sheet sheet = book.getSheet(0);//工作簿是从0开始的
                   for(rownum=1;rownum<sheet.getRows();rownum++){
                    name = sheet.getCell(0,rownum).getContents().trim();//参数顺序为（列，行）
                   sex = sheet.getCell(1,rownum).getContents().trim();//getContents().trim()是获取单元格内的值并去空格

                   try{
                         st = conn.prepareCall("insert into jfjl2_t values (?,?,?,?,?,?,?,?,?,?,?,?,?)");//这里是执行插入操作，可以换成别的如UPDATE
                         st.setString(1, name);
                         st.setString(2, sex);
                         st.execute();
                         st.close();
                         System.out.println("第"+rownum+"条记录！");
                         }catch (SQLException e) {
                                  e.printStackTrace();
                                  }
                         }
                   }finally{
                        book.close();
                        }
  }
  
  public static  Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{//配置JDBC连接
      String url = "jdbc:oracle:thin:@192.168.212.132:1521:TESTDB";
       Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
       con = DriverManager.getConnection(url, "user", "password");
       return con;
      
   }

  public static void main(String [] arg) throws BiffException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
   
           Connection co = loadEXCEL.getConnection();
           co.setAutoCommit(true);
           loadEXCEL.build(co);
           co.close();
           System.out.println("JUST OK!!!");
   
       }
 }