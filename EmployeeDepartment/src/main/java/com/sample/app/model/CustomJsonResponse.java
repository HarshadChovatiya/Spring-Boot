package com.sample.app.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Model to manage custom json response")
public class CustomJsonResponse {

	private String message;

	public CustomJsonResponse() {
		super();
	}

	public CustomJsonResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
