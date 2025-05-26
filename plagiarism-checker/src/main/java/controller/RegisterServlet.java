package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import model.User;
import dao.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fullName = req.getParameter("fullName");

		User u = new User();
		u.setUsername(username);
		u.setPassword(password); // nên hash nếu muốn bảo mật
		u.setFullName(fullName);

		boolean success = new UserDAO().register(u);
		if (success) {
			req.setAttribute("error", "Đăng ký thành công, vui lòng đăng nhập.");
			req.getRequestDispatcher("register.jsp").forward(req, res);
		} else {
			req.setAttribute("error", "Đăng ký thất bại hoặc tên đã tồn tại.");
			req.getRequestDispatcher("register.jsp").forward(req, res);
		}
	}
}
