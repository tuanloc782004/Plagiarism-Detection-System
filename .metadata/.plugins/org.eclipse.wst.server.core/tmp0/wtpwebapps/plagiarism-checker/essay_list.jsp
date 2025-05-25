<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="controller.EssayListServlet.EssayWithUser"%>
<%@ page import="model.Essay"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 8px;
	border: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

a.detail-link {
	text-decoration: none;
	color: blue;
}
</style>
</head>
<body>
	<h2>Danh sách bài luận</h2>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên bài luận</th>
				<th>Tác giả</th>
				<th>Ngày gửi</th>
				<th>Trạng thái</th>
				<th>Phần trăm đạo văn (%)</th>
				<th>Chi tiết đạo văn</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<EssayWithUser> essayList = (List<EssayWithUser>) request.getAttribute("essayList");
			if (essayList != null && !essayList.isEmpty()) {
				for (EssayWithUser item : essayList) {
					Essay essay = item.getEssay();
					User user = item.getUser();
			%>
			<tr>
				<td><%=essay.getId()%></td>
				<td><%=essay.getFilename()%></td>
				<td><%=user.getFullName() != null ? user.getFullName() : user.getUsername()%></td>
				<td><%=essay.getSubmittedAt()%></td>
				<td><%=essay.getStatus()%></td>
				<td><%=String.format("%.2f", essay.getPlagiarismPercent())%></td>
				<td><a class="detail-link"
					href="PlagiarismDetailServlet?essayId=<%=essay.getId()%>">Xem
						chi tiết</a></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="7">Chưa có bài luận nào.</td>
			</tr>
			<%
			}
			%>
		</tbody>
</body>
</html>