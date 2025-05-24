package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;
import utils.DBUtil;

public class UserDAO {
	public boolean insertUser(User user) {
		String sql = "INSERT INTO users(username, password, email) VALUES (?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword()); 
			stmt.setString(3, user.getEmail());

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
