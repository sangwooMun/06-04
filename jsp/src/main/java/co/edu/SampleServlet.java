package co.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet { // 웹에서 실행하려면
	// IOC
	@Override
	public void init(ServletConfig config) throws ServletException { // 최초 호출만
		System.out.println("init 호출");
	}
	
//	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		// 요청시 매번
		System.out.println("service 호출"); // 그다음부턴 service 코드 호출 req = 웹 브라우저에서 요청이 들어오면 저장해주는 곳, resp = 응답 처리를 해주는 것
		// req = 클라이언트의 요청처리 // resp = 클러이언트의 응답처리
		if(req.getMethod().equals("GET")){
			System.out.println("GET 요청");
			
		}else if(req.getMethod().equals("POST")){
			System.out.println("POST 요청");
		}
		
		String name = req.getParameter("name"); // name = ???&age = ??
		String age = req.getParameter("age");
		
		PrintWriter out = resp.getWriter();
		out.print("<h3>요청파라미터 : " + name + "</h3>");
		out.print("<h3>요청파라미터 : " + age + "</h3>");
		out.close();
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
//		}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
//		}
	
	@Override
	public void destroy() {
		System.out.println("destroy 호출"); // 마지막 한번 (자원 해제 수정 서버 재가동 등)
	}
}
