package chapter17;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MyServlet extends HttpServlet {
	
	public void init() throws ServletException{
		System.out.println("MyServlet 실행됨");
	}

}
