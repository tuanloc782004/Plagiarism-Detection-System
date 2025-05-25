<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.PlagiarismResult"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 80%;
	border-collapse: collapse;
	margin: 20px auto;
}

th, td {
	border: 1px solid #aaa;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #ddd;
}

a.back-link {
	margin-left: 10%;
	text-decoration: none;
	font-weight: bold;
	color: blue;
}
</style>
</head>
<body>
	<h2 style="text-align: center;">
		Chi tiết kết quả đạo văn cho bài luận ID:
		<%=request.getAttribute("essayId")%></h2>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Bài luận bị so sánh</th>
				<th>Phần trăm giống (%)</th>
				<th>Mô tả</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<PlagiarismResult> results = (List<PlagiarismResult>) request.getAttribute("results");
			if (results != null && !results.isEmpty()) {
				for (PlagiarismResult pr : results) {
					String matchedFilename = (String) request.getAttribute("matchedFilename_" + pr.getId());
			%>
			<tr>
				<td><%=pr.getId()%></td>
				<td><%=matchedFilename != null ? matchedFilename : "Không xác định"%></td>
				<td><%=String.format("%.2f", pr.getSimilarityPercent())%></td>
				<td><%=pr.getDescription()%></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="4">Không có dữ liệu chi tiết đạo văn.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<a href="EssayListServlet" class="back-link">← Quay lại danh sách
		bài luận</a>
</body>
</html>