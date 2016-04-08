package org.oscar.airtraffic.controllers;

import java.io.InputStream;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oscar.airtraffic.model.Log;
import org.oscar.airtraffic.model.ObjectWrapper;
import org.oscar.airtraffic.modelDaos.ApiKeyDao;
import org.oscar.airtraffic.modelDaos.LogsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LogInterceptor extends HandlerInterceptorAdapter{

	private LogsDao logsDao;
	private ApiKeyDao keyDao;
	
	@Autowired
	public void setDao(LogsDao log, ApiKeyDao keyDao){
		this.logsDao = log;
		this.keyDao = keyDao;
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		LocalTime lt = LocalTime.now();
		
		
		String test = request.getParameter("apikey");
		
		
		if (request.getMethod().equals("POST")){
			
			test= "S3cr3t";
		}
		
		System.out.println("APIKEY: "+test);
		
		Log log = new Log(new Date(), test, request.getPathInfo());
		logsDao.add(log);
		
		
		if(keyDao.searchApiKey(test))
			return super.preHandle(request, response, handler);
		else{
			System.out.println("Interceptor Error");
			response.sendError(808, "APIKEY Fail");
			return false;
		}
	}
	
}
