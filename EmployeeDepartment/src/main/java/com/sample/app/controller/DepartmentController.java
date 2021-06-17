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
import com.sample.app.model.Department;
import com.sample.app.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "Endpoints to manage crud operations of departments", tags = "Department")
public class DepartmentController {

	Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/department")
	@ApiOperation(value = "Add department details",
		notes = "provide necessary department details"
	)
	public ResponseEntity<CustomJsonResponse> addDepartment(@RequestBody Department department) throws Exception {
		try {
			return new ResponseEntity<CustomJsonResponse>(
					new CustomJsonResponse(departmentService.addDepartment(department)), HttpStatus.CREATED);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/department")
	@ApiOperation(value = "Get all the departments",
		notes = "Fetch details of all the departments"
	)
	public ResponseEntity<List<Department>> getAllDepartment() throws Exception {
		try {
			return new ResponseEntity<List<Department>>(
					departmentService.getAllDepartment(),
				HttpStatus.OK);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/department/{dep_id}")
	@ApiOperation(value = "Get department by Id",
		notes = "Provide an department ID to get the details"
	)
	public ResponseEntity<Department> getDepartmenById(@PathVariable("dep_id") int id) throws Exception {
		try {
			return new ResponseEntity<Department>(
					departmentService.getDepartmentById(id),
					HttpStatus.OK);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/department/{dep_id}")
	@ApiOperation(value = "Update department details",
		notes = "Provide department id and other details to update"
	)
	public ResponseEntity<Department> updateDepartment(@PathVariable("dep_id") int id, @RequestBody Department department) throws Exception {
		try {
			return new ResponseEntity<Department>(
					departmentService.updateDepartment(id, department),
				HttpStatus.OK);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/department/{dep_id}")
	@ApiOperation(value = "Delete department",
		notes = "provide an department id to delete that department"
	)
	public ResponseEntity<CustomJsonResponse> deleteDepartment(@PathVariable("dep_id") int id) throws Exception {
		try {
			return new ResponseEntity<CustomJsonResponse>(
					new CustomJsonResponse(departmentService.deleteDepartment(id)),
					HttpStatus.OK);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
