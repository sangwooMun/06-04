package co.edu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/todoServlet")
public class todoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public todoServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		ToDoDAO dao = new ToDoDAO();
		List<Todo> list = dao.doList();
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(list));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String job = req.getParameter("job");
		String seq = req.getParameter("seq");
		String content = req.getParameter("content");
		String status = req.getParameter("status");
		Todo td = new Todo();
		td.setSeq(seq);
		td.setContent(content);
		td.setStatus(status);
		
		ToDoDAO dao = new ToDoDAO();
		
		// 등록
		if(job.equals("add")) {
			dao.insertTodo(td);
			// {"retCode":"Success"}
			resp.getWriter().print("{\"retCode\":\"Success\"}");
			
		}else if(job.equals("del")) {
			dao.deleteTodo(td);
			resp.getWriter().print("{\"retCode\":\"Success\"}");
			
		}else {
			resp.getWriter().print("{\"retCode\":\"Success\"}");
		}
		
		
	}
	
	
}
