package com.example.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.emaillist.dao.EmaillistDao;
import com.example.emaillist.dao.EmaillistDaoImpl;
import com.example.emaillist.vo.EmailVo;

//@WebServlet(name="Emaillist", urlPatterns = "/el")
@WebServlet("/el")
public class EmaillistServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String actionName =req.getParameter("a");
		if("add".equals(actionName)) {
			String firstName = req.getParameter("first_name");
			String lastName = req.getParameter("last_name");
			String email = req.getParameter("email");
			
			EmailVo vo = new EmailVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			EmaillistDao dao = new EmaillistDaoImpl();
			int insertedCount=dao.insert(vo);
			//처리 후 list 페이지로 리다이렉트
			
			resp.sendRedirect(req.getContextPath()+"/el");
			System.out.println("insert act");
		}else if("delete".equals(actionName)){
			
			Long no = Long.valueOf(req.getParameter("no"));
			EmaillistDaoImpl dao = new EmaillistDaoImpl();
			int deletedCount = dao.delete(no);
			
			resp.sendRedirect(req.getContextPath()+"/el");
		}else {
			doGet(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파라미터 확인
		//a=form이면 가입 폼으로 forward
		String actionname = req.getParameter("a");
		
		if ("form".equals(actionname))
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/form.jsp");
			rd.forward(req, resp);
		}else {
		
		// DAO에서 목록을 받아서 JSP로 전달
		EmaillistDao dao = new EmaillistDaoImpl();
		List<EmailVo> list = dao.getList();
		//요청에 list를 추가
		req.setAttribute("list", list);
		
		//jsp로 요청을 전달
		
		//dispatcher 
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/index.jsp");
		rd.forward(req, resp);
		}
	}
	
}
