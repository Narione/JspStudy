package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/member/add")
public class MemberInsertServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/member/add.jsp").forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");			//name으로 불러옴
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		Connection connection=null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe","std124","oracle21c");
			String sql = "insert into member (id, name, password, email) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, password);
			statement.setString(4, email);
			// 조회(select) : 결과 ResultSet이고 메소드명은 executeQuery();
			// 등록, 수정, 삭제: 결과가 int이고 성공 실패 여부만 리턴해줌 메소드명은 executeUpdate();
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				//리다이렉트를사용
				response.sendRedirect("/member/list");
			} else {
				request.getRequestDispatcher("/member/add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			
		}
	}

}
