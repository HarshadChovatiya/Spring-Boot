package com.sample.app.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sample.app.model.Employee;
import com.sample.app.repository.EmployeeRepo;
import com.sample.app.repository.EmployeeRowMapper;

@Repository
public class EmployeeRepository implements EmployeeRepo{

	Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);
	
	@Autowired
	JdbcTemplate template;
	
	/* add employee */
	public String addEmployee(Employee employee) throws Exception {
		String query = "INSERT INTO employee VALUES(?,?,?,?,?,?)";
		try {
			template.update(query,
				employee.getEmpId(),
				employee.getName(),
				employee.getAge(),
				employee.getSalary(),
				employee.getCity(),
				employee.getDepId());
		}
		catch (Exception e) {
			logger.error("Error while adding employee " + e.getMessage());
			throw new Exception("Bad Request");
		}
		logger.info("Employee added");
		return "Employee details inserted";
	}
	
	/* get all employee */
	public List<Employee> getAllEmployee() throws Exception {
		String query = "SELECT * FROM employee";
		try {
			return template.query(query, new EmployeeRowMapper());
		} catch (Exception e) {
			logger.error("Error in getting all the employees " + e.getMessage());
			throw new Exception("Bad Request");
		}
	}
	
	/* get employee with specific id */
	@Cacheable(cacheNames = "employees", key = "#id")
	public Employee getEmployeeById(int id) throws Exception {
		String query = "SELECT * FROM employee WHERE empId = ?";
		try {
			return template.queryForObject(query, new EmployeeRowMapper(), id);
		}
		catch(EmptyResultDataAccessException e) {
			logger.error("No employee with given id exists " + e.getMessage());
			throw new EmptyResultDataAccessException("No employee with given id exists", id);
		}
		catch (Exception e) {
			logger.error("Error while fetching employee with specific id " + e.getMessage());
			throw new Exception("No employee with given id exists");
		}
	}
	
	/* update employee detail */
	@CachePut(cacheNames = "employees", key = "#id")
	public Employee updateEmployee(int id, Employee employee) throws Exception {
		String query;
		query = "UPDATE employee SET name = ? , age = ? , salary = ? , city = ?, depId = ? WHERE empId = ?";
		
		try {
			template.update(query,
				employee.getName(),
				employee.getAge(),
				employee.getSalary(),
				employee.getCity(),
				employee.getDepId(),
				employee.getEmpId());	
			return employee;
		} catch (Exception e) {
			logger.error("Error while updating employee detail " + e.getMessage());
			throw new Exception("Bad Request");
		}

	}
	
	/* delete employee */
	@CacheEvict(cacheNames = "employees", key = "#id")
	public String deleteEmployee(int id) throws Exception {
		String query = "DELETE FROM employee WHERE empId = ?";
		try {
			template.update(query, id);
			return "Employee deleted";
		} catch(Exception e) {
			logger.error("Error while deleting employee " + e.getMessage());
			throw new Exception("Employee with given id doesn't exists");
		}
	}
	
	/* get all the employees of specific department */
	public List<Employee> getEmployeeByDepartmentId(int id) throws Exception {
		String query = "SELECT * FROM employee WHERE depId = ?";
		try {
			return template.query(query, new EmployeeRowMapper() ,id);
		} catch(Exception e) {
			logger.error("Error while fetching employee department wise " + e.getMessage());
			throw new Exception("Department with the given id doesn't exists");
		}
	}
}
