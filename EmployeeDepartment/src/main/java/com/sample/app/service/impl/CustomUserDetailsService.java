package com.sample.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.app.model.JwtRequest;

import java.util.ArrayList;

import com.sample.app.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		JwtRequest jwtRequest = userRepository.isUserExists(userName);
		if(jwtRequest.getUsername() != null) {
			return new User(jwtRequest.getUsername(),
					jwtRequest.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found !");
		}
		
	}

}

