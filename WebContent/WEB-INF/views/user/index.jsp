<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
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
${message}
<br>

<br>
<a href="user/form.htm">
		<button type="button"  class="btn btn-warning">Thêm</button></a>
<table class="table table-hover">
	<tr>
		<th>Username</th>
		<th>Password</th>
		<th>Fullname</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach var="u" items = "${users}">
	<tr>
		<td>${u.username}</td>
		<td>${u.password}</td>
		<td>${u.fullname}</td>
		<td>
		<a href="user/form/${u.username }.htm?linkEdit">
		<button type="button"  class="btn btn-warning">Chỉnh xửa</button></a>
		</td>
		<td>
		<a href="user/index/${u.username }.htm?linkDelete">
		<button type="button"  class="btn btn-warning">xóa</button></a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>