<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Tải lên bài luận</h2>
	<form action="UploadEssayServlet" method="post"
		enctype="multipart/form-data">
		<input type="file" name="essayFile" accept=".txt,.docx" required />
		<button type="submit">Upload</button>
	</form>

	<c:if test="${not empty error}">
		<p style="color: red">${error}</p>
	</c:if>
	<c:if test="${param.success == '1'}">
		<p style="color: green">Tải lên thành công!</p>
	</c:if>
</body>
</html>