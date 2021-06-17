package com.sample.app.service;

import java.util.List;

import com.sample.app.model.Department;

public interface DepartmentService {

	String addDepartment(Department department) throws Exception;
	List<Department> getAllDepartment() throws Exception;
	Department getDepartmentById(int id) throws Exception;
	Department updateDepartment(int id, Department department) throws Exception;
	String deleteDepartment(int id) throws Exception;

}
