package controller;

import model.PlagiarismResult;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PlagiarismDetailServlet")
public class PlagiarismDetailServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int essayId = Integer.parseInt(request.getParameter("essayId"));
		List<PlagiarismResult> results = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT pr.*, e.filename AS matched_filename " + "FROM plagiarism_results pr "
					+ "LEFT JOIN essays e ON pr.matched_essay_id = e.id " + "WHERE pr.essay_id = ? "
					+ "ORDER BY pr.similarity_percent DESC";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, essayId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PlagiarismResult pr = new PlagiarismResult();
				pr.setId(rs.getInt("id"));
				pr.setEssayId(rs.getInt("essay_id"));
				pr.setMatchedEssayId(rs.getInt("matched_essay_id"));
				pr.setSimilarityPercent(rs.getDouble("similarity_percent"));
				pr.setDescription(rs.getString("description"));

				// Ta lưu thêm tên file matched để hiển thị
				request.setAttribute("matchedFilename_" + pr.getId(), rs.getString("matched_filename"));

				results.add(pr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("results", results);
		request.setAttribute("essayId", essayId);
		request.getRequestDispatcher("plagiarism_detail.jsp").forward(request, response);
	}
}
