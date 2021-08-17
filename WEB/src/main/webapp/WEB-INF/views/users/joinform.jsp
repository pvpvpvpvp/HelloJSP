<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyHome: JoinForm</title>
</head>
<body>
	<form method="POST" 
		action="<%=request.getContextPath() %>/users">
		<input type="hidden" name="a" value="join">
		<label for="name"></label>
		<input type="text" name="name" value="name">
		<label for="password"></label>
		<input type="text" name="password" value="password">
		<label for="email"></label>
		<input type="text" name="email" value="email">
		<label for="gender"></label>
		<input type="radio" name="gender" value="F" checked>여성
		<input type="radio" name="gender" value="M">남성
		
		<input type="submit"  value="가입"/>
	</form>

</body>
</html>