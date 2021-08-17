<%@page import="com.example.emaillist.vo.EmailVo"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//요청 객체에서 list속성 받아오기
List<EmailVo> list = (List<EmailVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emaillist (Model 2)</title>
</head>
<body>
	<h1>메일링 리스트(Model 2)</h1>
	<!-- email -->
	<%
	for (EmailVo vo : list) {
	%>
	<table border="1">
		<tr>
			<th>성</th>
			<td><%=vo.getLastName()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=vo.getFirstName()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=vo.getEmail()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- 삭제 폼 -->
				<form action="<%=request.getContextPath()%>/el"
					method="POST">
					<input type="hidden" name="a" value="delete" /> 
					<input type="hidden" name="no" value="<%=vo.getNo()%>" /> 
					<input type="submit" value="삭제" />
				</form>
			</td>
		</tr>
		</table>
		<br />
		<%
		}
		%>
		<p>
			<a href="<%=request.getContextPath()%>/el?a=form">추가
				이메일등록</a>
		</p>

	</table>
</body>
</html>