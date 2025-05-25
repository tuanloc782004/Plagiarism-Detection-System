package dao;

import java.sql.*;
import model.User;
import util.DBUtil;

public class UserDAO {

	public boolean register(User user) {
		String sql = "INSERT INTO users (username, password, full_name) VALUES (?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullName());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setFullName(rs.getString("full_name"));
				u.setCreatedAt(rs.getTimestamp("created_at"));
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
