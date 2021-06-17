package com.sample.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.model.Department;
import com.sample.app.repository.DepartmentRepo;
import com.sample.app.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Override
	public String addDepartment(Department department) throws Exception {
		return departmentRepo.addDepartment(department);
	}

	@Override
	public List<Department> getAllDepartment() throws Exception {
		return departmentRepo.getAllDepartment();
	}

	@Override
	public Department getDepartmentById(int id) throws Exception {
		return departmentRepo.getDepartmentById(id);
	}

	@Override
	public Department updateDepartment(int id, Department department) throws Exception {
		return departmentRepo.updateDepartment(id, department);
	}

	@Override
	public String deleteDepartment(int id) throws Exception {
		return departmentRepo.deleteDepartment(id);
	}

}
