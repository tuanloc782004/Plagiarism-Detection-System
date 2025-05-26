package controller;

import model.Essay;
import model.User;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EssayListServlet")
public class EssayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class EssayWithUser {
		private Essay essay;
		private User user;

		public EssayWithUser(Essay essay, User user) {
			this.essay = essay;
			this.user = user;
		}

		public Essay getEssay() {
			return essay;
		}

		public User getUser() {
			return user;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");

		if (currentUser == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		List<EssayWithUser> essayList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {

			// Lấy danh sách bài essay của user đang đăng nhập
			String sql = "SELECT e.*, u.username, u.full_name, u.created_at "
					+ "FROM essays e JOIN users u ON e.user_id = u.id " + "WHERE e.user_id = ? "
					+ "ORDER BY e.submitted_at DESC";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, currentUser.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Essay essay = new Essay();
				essay.setId(rs.getInt("id"));
				essay.setUserId(rs.getInt("user_id"));
				essay.setFilename(rs.getString("filename"));
				essay.setFilepath(rs.getString("filepath"));
				essay.setSubmittedAt(rs.getTimestamp("submitted_at"));
				essay.setStatus(rs.getString("status"));
				essay.setPlagiarismPercent(rs.getDouble("plagiarism_percent"));

				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setFullName(rs.getString("full_name"));
				user.setCreatedAt(rs.getTimestamp("created_at"));

				essayList.add(new EssayWithUser(essay, user));
			}

			// Đếm số lượng status bằng SQL
			String countSql = "SELECT " + "COUNT(*) AS total, "
					+ "SUM(CASE WHEN status = 'PENDING' THEN 1 ELSE 0 END) AS processing, "
					+ "SUM(CASE WHEN status = 'PROCESSED' THEN 1 ELSE 0 END) AS completed "
					+ "FROM essays WHERE user_id = ?";

			PreparedStatement countStmt = conn.prepareStatement(countSql);
			countStmt.setInt(1, currentUser.getId());
			ResultSet countRs = countStmt.executeQuery();

			int total = 0;
			int processing = 0;
			int completed = 0;

			if (countRs.next()) {
				total = countRs.getInt("total");
				processing = countRs.getInt("processing");
				completed = countRs.getInt("completed");
			}

			request.setAttribute("totalCount", total);
			request.setAttribute("processingCount", processing);
			request.setAttribute("completedCount", completed);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("essayList", essayList);
		request.getRequestDispatcher("essay_list.jsp").forward(request, response);
	}
}
