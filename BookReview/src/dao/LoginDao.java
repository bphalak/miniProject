package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import mode.User;

public class LoginDao {
	private User user;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public LoginDao(User user, Connection connection) {
		if (user != null && connection != null) {

			this.user = user;
			this.connection = connection;

			try {
				preparedStatement = connection.prepareStatement("select type from user where username = ? and password = ?");
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getPassword());

			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {

		}

	}

	public boolean authenticate() {
		try {
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if ("user".equals(resultSet.getString("type"))) {
					user.setType("user");
				} else {
					user.setType("admin");
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
