<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${alert != null }">
		<h3 class="alert alert-danger">${alert}</h3>
	</c:if>
	<form action="/hcmute/forgetpass" method="post">
		<div>
			<label for="username"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="username" required>
		</div>
		<div>
			<label for="password"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password1" required>
		</div>
		<div>
			<label for="password"><b>Retype Password</b></label> <input type="password"
				placeholder="Enter Password" name="password2" required>
		</div>
		<div>
			<button type="submit">Login</button>
		</div>

	</form>

</body>
</html>