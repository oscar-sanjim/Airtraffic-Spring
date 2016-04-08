package org.oscar.airtraffic.session;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean {

	private boolean logged;
	private int id;
	static int counter;	
	
	public SessionBean(){
		++counter;
		id = counter;
		logged = false;
	}
	
	public void message(){
		System.out.println("Welcome, you are currently user #"+ id);
	}
	
	public int getId(){
		return id;
	}
	
	public void login(){
		logged = true;
	}
	
	public void logout(){
		logged = false;
	}
	
	public boolean isLogged(){
		return logged;
	}
}
