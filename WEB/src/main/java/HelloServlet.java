import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상속
public class HelloServlet extends HttpServlet {

	// GET 방삭의 요청을 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		String name = req.getParameter("name");
		if (name == null) {
			name = "Anonymous";
		}

		PrintWriter out = resp.getWriter();
		out.println("<h3>Hello Servlet</h3>");
		out.println("<p>Welcome, " + name + "</p>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 페이지 응답 설정
		resp.setContentType("text/html; charset=UTF-8");
		// 파라미터 받기
		String firstname = req.getParameter("first_name");
		String lastname = req.getParameter("last_name");

		if (firstname == null || lastname == null) {
			firstname = lastname = "Anonymous";
		}

		PrintWriter out = resp.getWriter();
		out.println("<h3>Hello Servlet</h3>");
		out.println("<p>이름:" + firstname + "</p>");
		out.println("<p>성: " + lastname + "</p>");
		

	}

}
