package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession();
		String url = request.getServletPath();
		System.out.println(url);
		String contextPath = request.getContextPath();
		if (url.equals(""))
			url += "/";
		if ((url.startsWith("/") && !url.startsWith("/login") && !url.startsWith("/home"))) {// 若访问后台资源
																								// 过滤到login
			Cookie[] user = request.getCookies();
			if (user.length == 1) {// 转入登陆页面
				response.sendRedirect(contextPath + "/login.jsp");
				return;
			}
		}
		filterChain.doFilter(sRequest, sResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}