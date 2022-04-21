<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<table class="table table-hover">
		<tr>
			<th>Mã NV</th>
			<th>Tổng thành tích</th>
			<th>Tổng kỉ luật</th>
			<th>Tổng kết</th>
		</tr>
		<c:forEach var="a" items="${arrays}">
			<tr>
				<td>${a[0]}</td>
				<td>${a[1]}</td>
				<td>${a[2]}</td>
				<td>${a[1]*a[2]}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>