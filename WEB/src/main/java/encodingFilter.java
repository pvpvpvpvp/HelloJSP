import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class encodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("EncodingFilter::doChain Begin");
		//필터 적용
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		// 다음 연결된 필터로 요청과 응답 전달 
		chain.doFilter(req, resp); // 이것을 기준으로 다음 필터로 넘어감 없다면 service()를 실행후 아래를 실행함
		System.out.println("EncodingFilter::doChain End");
		
	}

	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("encoding filter init");
		Filter.super.init(filterConfig);
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("encoding filter destory");
		Filter.super.destroy();
	}
}
