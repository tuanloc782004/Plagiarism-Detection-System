<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		<input type="text" name="username" placeholder="Tên đăng nhập"
			required /> <input type="password" name="password"
			placeholder="Mật khẩu" required /> <input type="text"
			name="fullName" placeholder="Họ tên" required />
		<button type="submit">Đăng ký</button>
	</form>
	<c:if test="${not empty error}">
		<p style="color: red">${error}</p>
	</c:if>
</body>
</html>