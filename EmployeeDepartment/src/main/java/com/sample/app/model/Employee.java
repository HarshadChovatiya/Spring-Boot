package com.sample.app.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Model to manage Employee")
public class Employee {

	private int empId;
	private String name;
	private int age;
	private int salary;
	private String city;
	private int depId;
	
	public Employee() {
		super();
	}

	public Employee(int empId, String name, int age, int salary, String city, int depId) {
		super();
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.city = city;
		this.depId = depId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}
}
