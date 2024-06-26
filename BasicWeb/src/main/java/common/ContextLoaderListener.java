package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;


@WebListener
public class ContextLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce)  { 
		//DataSource를 사용하는 코드 작성
		// 서버에서 제공하는 DataSoruce 사용, new를 사용하면 서버가 직접 관리하는 것이 아님 
//		BasicDataSource basicDataSource = new BasicDataSource();
		// => 톰캣 서버의 context.xml에 JNDI방식으로 이름을 작성한 뒤
		// 톰캣 서버가 제공하는 DataSource를 사용하는 방식으로 작성해야 함
		
		try {
			Context context = new InitialContext();
			DataSource dataSouce = (DataSource)context.lookup("java:/comp/env/jdbc/nextit");
			ServletContext servletContext = sce.getServletContext();
			servletContext.setAttribute("dataSource", dataSouce);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

}
