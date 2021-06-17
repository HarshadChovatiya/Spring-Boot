package com.sample.app.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Model to manage Department")
public class Department {
	
	private int depId;
	private String name;
	
	public Department() {
		super();
	}

	public Department(int depId, String name) {
		super();
		this.depId = depId;
		this.name = name;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
