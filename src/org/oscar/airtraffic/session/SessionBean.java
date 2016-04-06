package org.oscar.airtraffic.session;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean {

	static int id;
	
	public SessionBean(){
		++id;
	}
	
	public void message(){
		System.out.println("Welcome, you are currently user #"+id);
	}
	
	public int getId(){
		return id;
	}
}
