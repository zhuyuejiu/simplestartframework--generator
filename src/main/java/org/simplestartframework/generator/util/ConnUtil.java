package org.simplestartframework.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author ranger
 * 
 */
public class ConnUtil {

	public static Connection getConnection() {
		Properties prop = PropertiesUtil.getProp("config/sys.properties");
		Connection conn = null;
		try {
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
