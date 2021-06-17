package com.sample.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.model.Employee;
import com.sample.app.repository.EmployeeRepo;
import com.sample.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public String addEmployee(Employee employee) throws Exception {
		return employeeRepo.addEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployee() throws Exception {
		return employeeRepo.getAllEmployee();
	}

	@Override
	public Employee getEmployeeById(int id) throws Exception {
		return employeeRepo.getEmployeeById(id);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) throws Exception {
		return employeeRepo.updateEmployee(id, employee);
	}

	@Override
	public String deleteEmployee(int id) throws Exception {
		return employeeRepo.deleteEmployee(id);
	}

	@Override
	public List<Employee> getEmployeeByDepartmentId(int id) throws Exception {
		return employeeRepo.getEmployeeByDepartmentId(id);
	}

}
