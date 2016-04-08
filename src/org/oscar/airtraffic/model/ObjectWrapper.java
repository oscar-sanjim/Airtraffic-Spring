package org.oscar.airtraffic.model;

import org.oscar.airtraffic.modelAirTrafficData.Airline;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectWrapper {
	private String apikey;
	private Object data;
	
	public ObjectWrapper() {
		super();
		this.apikey = "DEFAULT";
		this.data = null;
	}
	
	public ObjectWrapper(String apikey, Object data) {
		super();
		this.apikey = apikey;
		this.data = data;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}