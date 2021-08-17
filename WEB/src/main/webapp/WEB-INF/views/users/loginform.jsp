<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyHome: Login Form</title>
</head>
<body>
	<h1>Login</h1>
	<form method="post" 
		action="<%= request.getContextPath() %>/users">
		
		<input type="hidden" name="a" value="login"/>
		<label for = "email">이메일</label>
		<input type="text" name="email" value="email"/>
		<label for = "password">비밀번호</label>
		<input type="text" name="password" value="password"/><br/>
		<input type="submit" value="로그인"/>
		</form>
</body>
</html>