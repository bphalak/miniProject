package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class MyConnection {
	
	public Connection connect() throws SQLException, ClassNotFoundException{
		/*PropertyConfigurator.configure("D:/MiniProject/workspace/BookReview/project.properties");
		Properties prop = new Properties();*/
		
		Class.forName("com.mysql.jdbc.Driver");

		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/miniproject","root","root");

		return connect;
	}

}
