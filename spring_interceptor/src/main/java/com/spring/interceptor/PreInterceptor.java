package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PreInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean result = true;
		
		String state = request.getParameter("state");
		
		if(state==null || !state.equals("go")) {
			response.sendRedirect(request.getContextPath());
			result=false;
		}
		return result;
	}

	
	
}
