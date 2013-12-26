package br.com.tavernadodragao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		if (uri.endsWith("/") || uri.endsWith("login") || uri.endsWith("signup") ||
				uri.contains("resources"))
			return true;
		
		if (request.getSession().getAttribute("logged") != null)
			return true;
		
		response.sendRedirect("/");
		
		return false;
	}
}
