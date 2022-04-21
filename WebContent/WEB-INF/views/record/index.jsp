<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	${message }
	<form:form action="record/index.htm" modelAttribute="record"
		method="post">
		<div>
			<label>Nhân viên</label>
			<form:select path="staffs.id" items="${staffsSel}" itemValue="id"
				itemLabel="name" />
		</div>
		<div>
			<label>Loại</label>
			<form:radiobutton path="type" value="true" label="Thành tích" />
			<form:radiobutton path="type" value="false" label="Kỷ luật" />
		</div>
		<div>
			<label>Lý do</label>
			 <form:textarea path="reason" rows="3" />
		</div>
		<div>
			<button name= "${btnupdate ? 'btnupdate' : 'Insert'}" >${btnupdate ? 'Update' : 'Insert'}</button>
		</div>
	</form:form>
	<br>
	<table class="table table-hover">
		<tr>
			<th>Nhân viên</th>
			<th>Loại</th>
			<th>Lí do</th>
			<th>Ngày</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="r" items="${records}">
			<tr>
				<td>${r.staffs.name}</td>
				<td>${r.type? 'Thành tích' : 'Kỉ luật'}</td>
				<td>${r.reason}</td>
				<td>${r.date}</td>
				<td><a href="record/index/${r.id}.htm?linkEdit">
						<button type="button" class="btn btn-warning">Chỉnh xửa</button>
				</a></td>
				<td><a href="record/index/${r.id}.htm?linkDelete">
						<button type="button" class="btn btn-warning">xóa</button>
				</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>