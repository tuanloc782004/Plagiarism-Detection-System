package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private static final String URL = "jdbc:mysql://localhost:3306/plagiarism_checker";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
