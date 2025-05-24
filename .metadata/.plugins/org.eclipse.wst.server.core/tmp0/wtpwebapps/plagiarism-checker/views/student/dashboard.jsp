<%@ page import="model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<h2>Chào học sinh: <%= user.getUsername() %></h2>
<a href="../logout.jsp">Đăng xuất</a>
