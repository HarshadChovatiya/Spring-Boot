package com.sample.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sample.app.model.Department;

public class DepartmentRowMapper implements RowMapper<Department>{

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department department = new Department();
		department.setDepId(rs.getInt("depId"));
		department.setName(rs.getString("name"));
		return department;
	}

}
