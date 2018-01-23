package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class LogHelper {
	private LogHelper() {
		
	}
	
	private static LogHelper instance;
	
	public static LogHelper getInstance() {
		if (instance == null) {
			instance = new LogHelper();
		}
		
		return instance;
	}
	public void Init()
    {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/log4j.properties");
			
			String root=Utils.getWebrootPath();
	        //String logFilesPath = root + "WEB-INF" + File.separator + "log";
	        String logFilesPath = root + "WEB-INF";
            System.setProperty("logFilesPath", logFilesPath);
            System.out.println(System.getProperty("logFilesPath"));
	        /* String filePath=root+"WEB-INF" + File.separator+"config"+File.separator+"log4j.properties"; 
	         PropertyConfigurator.configure(System.getProperties());
	         PropertyConfigurator.configure(filePath); */
	         //logger.setLevel(Log4jLevel.DEBUG);
			
			Properties p = new Properties();
			p.load(inputStream);
	        if(p != null)
	        {
	            PropertyConfigurator.configure(p);  //载入配置文件
	            System.out.println("日志的配置文件读取成功");
	             
	        }
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
    }

}
