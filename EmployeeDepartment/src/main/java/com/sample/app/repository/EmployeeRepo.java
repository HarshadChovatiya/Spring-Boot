package com.sample.app.repository;

import java.util.List;

import com.sample.app.model.Employee;

public interface EmployeeRepo {
	
	String addEmployee(Employee employee) throws Exception;
	List<Employee> getAllEmployee() throws Exception;
	Employee getEmployeeById(int id) throws Exception;
	Employee updateEmployee(int id, Employee employee) throws Exception;
	String deleteEmployee(int id) throws Exception;
	List<Employee> getEmployeeByDepartmentId(int id) throws Exception;
	
}
