package org.oscar.airtraffic.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oscar.airtraffic.session.SessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PageInterceptor extends HandlerInterceptorAdapter{
	
	/*Sessions*/
	@Autowired
	public SessionBean session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String address = request.getPathInfo();
		
		if(address.equals("/loginFail")){
			return super.preHandle(request, response, handler);
		}
		
		if(address.equals("/") && session.isLogged()){
			response.sendRedirect("/AirTraffic/welcome");
		}
		
		if(!address.equals("/") && !session.isLogged() && !address.contains("resources")){
			//int userId = session.getId();
			System.out.println("Invalid acces: "+address);
			response.sendRedirect("/AirTraffic/");
		}
		return super.preHandle(request, response, handler);
	}

}
