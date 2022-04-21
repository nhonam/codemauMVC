<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Spring MVC</title>
<base href="${pageContext.servletContext.contextPath}/">
<style>
	div {
		display: inline-block;
		text-align: center; ,
		margin: 5px, padding:5px;
		border: 1px dotted orangered;
		border-radius: 5px;
	}
</style>
</head>
<body>
	<h1>EL & JSTL</h1>
	<div>
		<img src="${applicationScope.photo}"> <br> <strong>${name}</strong> <em>${salary*applicationScope.level}</em>
	</div>
	<div>
		<img src="${sessionScope.photo}"> <br> <strong>${name}</strong> <em>${salary*sessionScope.level}</em>
	</div>
	<div>
		<img src="${requestScope.photo}"> <br> <strong> ${name} </strong> <em>${salary*requestScope.level}</em>
	</div>
</body>
</html>