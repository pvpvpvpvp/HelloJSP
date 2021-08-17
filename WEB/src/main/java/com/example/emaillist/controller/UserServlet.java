package com.example.emaillist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.emaillist.dao.UserDao;
import com.example.emaillist.dao.UserDaoImpl;
import com.example.emaillist.vo.UserVo;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	// get 요청 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String actionName = req.getParameter("a");
		if ("joinform".equals(actionName)) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);
		} else if ("joinsuccess".equals(actionName)) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);
		} else if ("loginform".equals(actionName)) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendError(404);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");

		if ("join".equals(actionName)) {
			// 가입절차 수행
			UserVo vo = new UserVo();
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			

			vo.setName(name);
			vo.setPassword(password);
			vo.setEmail(email);
			vo.setGender(gender);

			System.out.println(vo);

			UserDao dao = new UserDaoImpl();
			int insertedCount = dao.insert(vo);

			if (insertedCount == 1) {
				// 가입성공 성공페이지로 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/users?a=joinsuccess");
			} else {
				// 가입실패 가입 폼으로 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/users?a=joinform");
			}
		} else if ("login".equals(actionName)) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			System.out.printf("로그인정보 email=%s password=%s", email, password);

			UserDao dao = new UserDaoImpl();

			UserVo vo = dao.getUserByEmailAndPassword(email, password);
			
			if (vo == null) {
				System.out.println("사용자 없음!");
				resp.sendRedirect(req.getContextPath() + "/users?a=loginform");

			}else {
				System.out.println("사용자 발견!"+vo);
				resp.sendRedirect(req.getContextPath()); // == /WEB
				
			}

		}else {
			resp.sendRedirect(req.getContextPath());
		}

	}

}
