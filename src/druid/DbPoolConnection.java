package druid;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DbPoolConnection {
	private static DbPoolConnection databasePool = null;
	private static DruidDataSource dds = null;
	private DbPoolConnection() {}
	static {
		//Properties properties = loadPropertyFile("db_server.properties");
		try {
			Properties properties = new Properties();
			InputStream is = DbPoolConnection.class.getResourceAsStream("/db_server.properties");
			properties.load(is);
			dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized DbPoolConnection getInstance() {
		if (null == databasePool) {
			databasePool = new DbPoolConnection();
		}
		return databasePool;
	}
	public DruidPooledConnection getConnection() throws SQLException {
		return dds.getConnection();
	}
}