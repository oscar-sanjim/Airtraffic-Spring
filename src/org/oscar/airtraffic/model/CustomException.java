package org.oscar.airtraffic.model;

public class CustomException {
	private String message;
	
	public CustomException(){
		this.message = "";
	}
	
	public CustomException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
