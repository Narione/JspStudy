package chapter17;

import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

//@WebServlet(loadOnStartup = 1,
//		initParmas = {
//				@WebInitParam(name = "jdbcdriver", value="oracle.jdbc.OracleDriver"),
//				@WebInitParam(name = "uri", value = "jdbc:oracle:thin:@nextit.or.kr:1521:xe"),
//				@WebInitParam(name = "name", value = "std124"),
//				@WebInitParam(name = "password", value = "oracle21c"),
//				@WebInitParam(name = "poolName",value = "chapter17")
//		})
public class DBCPInit extends HttpServlet {
	
	public void init() throws ServletException {
		System.out.println("JDBC 드라이버 로딩중...");
		loadJDBCDriver();
		System.out.println("Connection Pool 생성중...");
		initConnectionPool();
		System.out.println("Connection Pool 생성 완료");
	}
	
	/**
	 * JDBC Driver를 로딩하는 메소드
	 */
	private void loadJDBCDriver() {
		String driverClass = getInitParameter("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	
	/**
	 * 커넥션풀을 이용해서 데이터베이스 연결
	 */
	private void initConnectionPool() {
		String connectionUri = getInitParameter("uri");
		String userName = getInitParameter("name");
		String userPassword = getInitParameter("password");
		try {
		DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectionUri, userName, userPassword);
		PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connectionFactory, null);
		poolableConnFactory.setValidationQuery("select 1 from dual");
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<>();
		poolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(1000L * 60L * 5L));
		poolConfig.setMinIdle(4);
		poolConfig.setMaxTotal(50);
		
		GenericObjectPool connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
		poolableConnFactory.setPool(connectionPool);
		
		Class.forName("org.apache.commons.dbcp2.PoolingDriver");
		PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		String poolName = getInitParameter("poolName");
		driver.registerPool("poolName", (ObjectPool<? extends Connection>) connectionPool);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
