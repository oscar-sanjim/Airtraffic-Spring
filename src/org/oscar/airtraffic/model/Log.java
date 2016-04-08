package org.oscar.airtraffic.model;

import java.util.Date;

public class Log {
	private Date date;
	private String apiKey;
	private String route;
	
	public Log() {
		super();
		this.date = null;
		this.apiKey = "";
		this.route = "";
	}
	
	public Log(Date date, String apiKey, String route) {
		super();
		this.date = date;
		this.apiKey = apiKey;
		this.route = route;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	
}
