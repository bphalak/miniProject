package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mode.Book;

public class BookSearchDAO {
	Connection connect;
	String bookName;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Book book;

	public BookSearchDAO(Connection connect, String bookName) {

		this.connect = connect;
		this.bookName = bookName.toLowerCase();

	}

	public Book searchName() throws SQLException {

		preparedStatement = connect
				.prepareStatement("Select * from def_books natural join def_master where title ?");
		preparedStatement.setString(1, bookName);

		resultSet = preparedStatement.executeQuery();

		return new Book(resultSet.getInt("bookid"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getInt("genre"), resultSet.getInt("status"), resultSet.getString("creationDate"), resultSet.getInt("createdBy"), resultSet.getString("modificationDate"), resultSet.getInt("modifiedBy"), resultSet.getString("description"));

	}

}
