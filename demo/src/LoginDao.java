package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class LoginDao {
	private User user;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public LoginDao(User user, Connection connection) {
		if (user != null && connection != null) {
			this.user = user;
			this.connection = connection;

			try {
				statement = connection.prepareStatement(
						"select type from userdetails where username = ? and password = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				statement.setString(1, user.getUserName());
				statement.setString(2, user.getPassword());

			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {

		}

	}

	public boolean authenticate() {
		try {
			resultSet = statement.executeQuery();
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
