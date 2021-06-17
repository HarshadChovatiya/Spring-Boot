package com.sample.app.repository;

import com.sample.app.model.JwtRequest;

public interface UserRepo {

	int addUser(String name,String password);
	JwtRequest isUserExists(String name);
}
