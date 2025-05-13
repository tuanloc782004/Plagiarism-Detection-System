package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import utils.DBContext;

public class UserDAO {
	public User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password); 

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
