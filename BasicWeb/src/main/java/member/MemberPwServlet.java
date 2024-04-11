package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/password")
public class MemberPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deletedId = request.getParameter("id");
		request.setAttribute("deletedId", deletedId);
		request.getRequestDispatcher("/member/password.jsp" ).forward(request, response);

	}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String deletedId = request.getParameter("id");
	String password = request.getParameter("password");
	String modiPw = request.getParameter("modiPw");
	String confirmedPw = request.getParameter("confirmedPw");
	
	Connection connection=null;
	PreparedStatement statement=null;
	ResultSet resultSet=null;
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe","std124","oracle21c");
		String sql = "select id, name, email from member where id = ? and password = ?" ;
		statement =connection.prepareStatement(sql);
		statement.setString(1, deletedId);
		statement.setString(2, password);
		resultSet = statement.executeQuery();
		MemberVO vo=null;
		if  (resultSet.next()) {
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			vo = new MemberVO(id, name, null, email);
			
		}
		if (vo!=null) {
			//비밀번호 변경
			String updateSql = "update member set password = ?, modify_date= sysdate where id = ?";
			statement = connection.prepareStatement(updateSql);
			statement.setString(1, modiPw);
			statement.setString(2, deletedId);
			
			int executeUpdate = statement.executeUpdate();
			if(executeUpdate> 0 ) {
				response.sendRedirect("/member/view?id="+deletedId);
			}
		} else {
			// 비밀번호 변경 페이지로 그대로 이동
			request.getRequestDispatcher("/member/password.jsp").forward(request, response);
			request.setAttribute("id", deletedId);
		}
		// request에 회원목록 데이터를 보관한다.
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			connection.close();
			statement.close();
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	}

}
