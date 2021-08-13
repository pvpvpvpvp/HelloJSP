<%@page import="com.example.emaillist.dao.EmaillistDaoImpl"%>
<%@page import="com.example.emaillist.dao.EmaillistDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  
	//파라미터 받아오기
	Long pk = Long.valueOf(request.getParameter("no"));
	EmaillistDao dao =new EmaillistDaoImpl();
	//삭제
	dao.delete(pk);
	
	response.sendRedirect(request.getContextPath()+"/emaillist/");
   
    %>
