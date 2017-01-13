package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyConnection {
	private static Connection connection;
	
	private MyConnection(){
		
	}

	public static Connection getConnection() {
		PropertyConfigurator.configure("D:/MiniProject/BookReviewManagementSystem/log4j.properties");
		Logger logger = Logger.getRootLogger();
		Properties properties = new Properties();
		if (connection == null) {
			try {
				properties.load(new FileInputStream("D:/MiniProject/BookReviewManagementSystem/db.properties"));
				Class.forName(properties.getProperty("driver"));
				connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
						properties.getProperty("password"));
			} catch (SQLException | IOException | ClassNotFoundException e) {
				if (e instanceof SQLException) {
					logger.warn("Failed to connect with database.");
				} else if (e instanceof IOException) {
					logger.warn("unable to find properties file.");
				} else {
					logger.warn("Unable to load driver.");
				}
			}
		}
		return connection;
	}
}
