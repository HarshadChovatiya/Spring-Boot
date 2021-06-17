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

import com.sample.app.model.Department;
import com.sample.app.repository.DepartmentRepo;
import com.sample.app.repository.DepartmentRowMapper;

@Repository
public class DepartmentRepository implements DepartmentRepo{
	
	Logger logger = LoggerFactory.getLogger(DepartmentRepository.class);
	
	@Autowired
	JdbcTemplate template;
	
	/* add department */
	public String addDepartment(Department department) throws Exception {
		String query = "INSERT INTO department(depId, name) VALUES (?,?)";
		try {
			template.update(query,
					department.getDepId(),
					department.getName());
		} catch(Exception e) {
			logger.error("Error while adding Department" + e.getMessage());
			throw new Exception(e.getMessage());
		}
		logger.info("Department added");
		return "department details added";
	}
	
	/* get all the departments */
	public List<Department> getAllDepartment() throws Exception {
		String query = "SELECT * FROM department";
		try {
			return template.query(query, new DepartmentRowMapper());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/* get department by id */
	@Cacheable(cacheNames = "departments", key = "#id")
	public Department getDepartmentById(int id) throws Exception {
		String query = "SELECT * FROM department WHERE depId = ?";
		try {
			return template.queryForObject(query, new DepartmentRowMapper(), id);
		}
		catch(EmptyResultDataAccessException e) {
			logger.error("No department with given id exists " + e.getMessage());
			throw new EmptyResultDataAccessException("No department with given id exists", id);
		}
		catch (Exception e) {
			logger.error("Error while getting department by id " + e.getMessage());
			throw new Exception("department does not exists");
		}
	}
	
	/* update department details */
	@CachePut(cacheNames = "departments", key = "#id")
	public Department updateDepartment(int id, Department department) throws Exception {
		String query = "UPDATE department SET name = ? WHERE depId = ?";
		try {
			int rowUpdated = template.update(query, department.getName(), id);
			if(rowUpdated <= 0) {
				throw new Exception("department with given id does not exists");
			}
			return department;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/* delete department */
	@CacheEvict(cacheNames = "departments", key = "#id")
	public String deleteDepartment(int id) throws Exception {
		String query = "DELETE FROM department WHERE depId = ?";
		try {
			int rowDeleted = template.update(query, id);
			if(rowDeleted <= 0) {
				throw new Exception("No department with given id exists");
			}
			return "department deleted successfully";
		} catch(Exception e) {
			logger.error("Error while deleting department " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
}
