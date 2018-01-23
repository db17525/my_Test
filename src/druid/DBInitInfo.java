package druid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class DBInitInfo {
    public  static List<DBbean> beans = null;
    static{
        beans = new ArrayList<DBbean>();
        // 这里数据 可以从xml 等配置文件进行获取
        // 为了测试，这里我直接写死
        DBbean beanOracle = new DBbean();
        beanOracle.setDriverName("oracle.jdbc.driver.OracleDriver");
        beanOracle.setUrl("jdbc:oracle:thin:@10.248.2.111:1521:orcl");
        beanOracle.setUserName("etrackchb");
        beanOracle.setPassword("etrackchb");

        beanOracle.setMinConnections(5);
        beanOracle.setMaxConnections(100);

        beanOracle.setPoolName("oraclePool");
        beans.add(beanOracle);
    }
}
