<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix = "c" %>
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
<table class = "table table-hover">
	<tr> 
		<th>Mã NV</th>
		<th>Họ và Tên</th>
		<th>Giới tính</th>
		<th>Phòng</th>
	</tr>
	<c:forEach var = "s" items = "${staffs}">
		<tr>
			<td>${s.id}</td>
			<td>${s.name}</td>
			<td>${s.gender?'Nam':'Nữ'}</td>
			<td>${s.depart.name}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>