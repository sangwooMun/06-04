package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDAO extends DAO{
	
	public List<Todo> doList(){
		connect();
		List<Todo> list = new ArrayList<Todo>();
		try {
			psmt = conn.prepareStatement("select * from do_list order by 1");
			rs = psmt.executeQuery();
			while(rs.next()) {
				Todo td = new Todo();
				td.setSeq(rs.getString("do_id"));
				td.setContent(rs.getString("todo"));
				td.setStatus(rs.getString("status"));
				
				list.add(td);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertTodo(Todo to) {
		System.out.println("등록");
		String sql = "insert into do_list(do_id, todo, status) values(?,?,?)";
				
		connect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, to.getSeq());
			psmt.setString(2, to.getContent());
			psmt.setString(3, to.getStatus());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Todo deleteTodo(Todo to) {
		System.out.println("삭제");
		String sql = "delete from do_list where content=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, to.getContent());
			psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return to;
	}
	
	public Todo updateTodo(Todo to) {
		connect();
		String sql = "update do_list set todo=?, status=? where do_id=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, to.getContent());
			psmt.setString(2, to.getStatus());
			psmt.setString(3, to.getSeq());
			
			int r = psmt.executeUpdate();
			System.out.println(r + " 수정");
			if(r > 0) {
				return to;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return null;
	}

}
