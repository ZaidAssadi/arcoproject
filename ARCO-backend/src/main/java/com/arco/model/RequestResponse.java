package com.arco.model;

public class RequestResponse {
	private Boolean hasError = false;
    private String message;
    private Object model;
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
	public Boolean getHasError() {
		return hasError;
	}
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public Object getModel() {
		return model;
	}
	public void setModel(Object model) {
		this.model = model;
	}
}