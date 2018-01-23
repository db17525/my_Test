package file;

import java.io.File;  
import jxl.*;   
public class ReadExcel{  
    public static void main(String[] args) {  
        Sheet sheet;  
        Workbook book;  
        Cell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13;
        try {
        	String files = "D:/渔船信息";
        	File file = new File(files);
        	File [] dir = file.listFiles();
        	for (File excFile : dir) {
			    System.out.println(excFile.getAbsolutePath());
	            book= Workbook.getWorkbook(new File("D://50287.xls"));   
	              
	            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)  
	            sheet=book.getSheet(0);   
	              
	            //获取每一行的单元格   
	            cell1=sheet.getCell(1,2);//（列，行）  
	            cell2=sheet.getCell(4,2);  
	            cell3=sheet.getCell(6,2);  
	            cell4=sheet.getCell(1,3);
	            cell5=sheet.getCell(3,3);  
	            cell6=sheet.getCell(6,3);  
	            cell7=sheet.getCell(1,4);
	            cell8=sheet.getCell(3,4); 
	            cell9=sheet.getCell(3,5);
	            cell10=sheet.getCell(1,6);
	            cell11=sheet.getCell(3,6);  
	            cell12=sheet.getCell(6,6);
	            cell13=sheet.getCell(1,7);
	                
	            System.out.println(cell1.getContents()+"#"+cell2.getContents()+"#"+cell3.getContents()+"#"+cell4.getContents()  
	                    +"#"+cell5.getContents()+"#"+cell6.getContents()+"#"+cell7.getContents()+"#"+cell8.getContents()+"#"+cell9.getContents()  
	                    +"#"+cell10.getContents()+"#"+cell11.getContents()+"#"+cell12.getContents()+"#"+cell13.getContents());    
	            book.close();
	        }
        } catch(Exception e){
        	e.printStackTrace();
        }
    }  
}  