package com.sample.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sample.app.model.JwtRequest;
import com.sample.app.repository.UserRepo;
import com.sample.app.repository.UserRowMapper;


@Repository
public class UserRepository implements UserRepo{

	@Autowired
	JdbcTemplate template;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int addUser(String name,String password) {
		String query = "INSERT INTO user VALUES(?,?)";
		return template.update(query,name,passwordEncoder.encode(password));
	}
	
	public JwtRequest isUserExists(String name) {
		String query = "SELECT * FROM user WHERE name = ?";
		
		JwtRequest jwtRequest;
		
		try {
			jwtRequest = template.queryForObject(query, new UserRowMapper(), name);
		} catch (Exception e) {
			return new JwtRequest();
		}
		
		return jwtRequest;
	}
}