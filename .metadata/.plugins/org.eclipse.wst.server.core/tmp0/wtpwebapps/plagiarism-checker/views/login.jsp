<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Đăng nhập</title>
</head>
<body>
	<h2>Đăng nhập</h2>
	<form action="login" method="post">
		<label>Tên đăng nhập:</label><input type="text" name="username"
			required /><br /> <label>Mật khẩu:</label><input type="password"
			name="password" required /><br />
		<button type="submit">Đăng nhập</button>
	</form>
	<p style="color: red;">${error}</p>
</body>
</html>
