package common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter(filterName="CharacterEncodingFilter", urlPatterns="/*", initParams= {@WebInitParam(name="encoding", value="UTF-8")})
public class CharacterEncodingFilter extends HttpFilter implements Filter {
	
	
	private FilterConfig config;

	@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			config = filterConfig;
		}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 실행해야할 내용 기술
		request.setCharacterEncoding(config.getInitParameter("encoding"));
//		request.setCharacterEncoding("UTF-8");
		// 가장 마지막에 위치
		chain.doFilter(request, response);
	}
}
