package test.dbase;
import java.io.InputStream;
import java.util.Properties;
 
public class ReadProperties {
    static private String driver = null;
    static private String url = null;
    static private String user = null;
    static private String password = null;
    static{
        loads();
    }
    synchronized static public void loads(){
        if(driver == null || url == null || user == null || password == null){
            InputStream is = ReadProperties.class.getResourceAsStream("/db.properties");
            Properties dbproperties = new Properties();
            try {
                dbproperties.load(is);
                        
                driver = dbproperties.getProperty("driver").toString();
                url = dbproperties.getProperty("url").toString();  
                user = dbproperties.getProperty("user").toString();  
                password = dbproperties.getProperty("password").toString(); 
                    
            }
            catch (Exception e) {
                System.err.println("不能读取属性文件. " + "请确保db.properties在CLASSPATH指定的路径中");
            }
        }
    }
      
    public static String getDriver() {
        if(driver==null)
            loads();
        return driver;
    }
     
    public static String getUrl() {
        if(url==null)
            loads();
        return url;
    }
     
    public static String getUser() {
        if(user==null)
            loads();
        return user;
    }
     
    public static String getPassword() {
        if(password==null)
            loads();
        return password;
    }
  public static void main(String[] args) {
	loads();
}
}