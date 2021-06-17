package com.sample.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.CustomJsonResponse;
import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "Endpoints to manage crud operations of employee", tags = "Employee")
public class EmployeeController {
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/employee", produces = "application/json")
	@ApiOperation(value = "Add employee details",
		notes = "provide necessary employee details"
	)
	public ResponseEntity<CustomJsonResponse> addEmployee(@RequestBody Employee employee) throws Exception {
		try {
			return new ResponseEntity<CustomJsonResponse>(
					new CustomJsonResponse(employeeService.addEmployee(employee)),
					HttpStatus.CREATED);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employee", produces = "application/json")
	@ApiOperation(value = "Get all the employees",
		notes = "Fetch details of all the employees"
	)
	public ResponseEntity<List<Employee>> getAllEmployee() throws Exception {
		try {
			return new ResponseEntity<List<Employee>>(
					employeeService.getAllEmployee(),
					HttpStatus.OK);			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employee/{emp_id}", produces = "application/json")
	@ApiOperation(value = "Get employee by Id",
		notes = "Provide an employee ID to get the details"
	)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("emp_id") int id) throws Exception {
		try {
			return new ResponseEntity<Employee>(
					employeeService.getEmployeeById(id),
				HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/employee/{emp_id}", produces = "application/json")
	@ApiOperation(value = "Update employee details",
		notes = "Provide employee id and other details to update"
	)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("emp_id") int id,
			@RequestBody Employee employee) throws Exception {
		try {
			return new ResponseEntity<Employee>(
					employeeService.updateEmployee(id, employee),
				HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/employee/{emp_id}", produces = "application/json")
	@ApiOperation(value = "Delete employee",
		notes = "provide an department id to delete that employee"
	)
	public ResponseEntity<CustomJsonResponse> deleteEmployee(@PathVariable("emp_id") int id) throws Exception {
		try {
			return new ResponseEntity<CustomJsonResponse>(
					new CustomJsonResponse(employeeService.deleteEmployee(id)), HttpStatus.OK);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employee/department/{dep_id}",
			produces = "application/json")
	@ApiOperation(value = "Get employee working in a specific department",
			notes = "provide an department id to fetch all the employees of that department"
			)
	public ResponseEntity<List<Employee>> getEmployeeByDepartmentId(
			@PathVariable("dep_id") int id) throws Exception {
		try {
			return new ResponseEntity<List<Employee>>(
					employeeService.getEmployeeByDepartmentId(id),
					HttpStatus.OK);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
