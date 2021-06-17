package com.sample.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.helper.JwtUtil;
import com.sample.app.model.JwtRequest;
import com.sample.app.model.JwtResponse;
import com.sample.app.repository.impl.UserRepository;
import com.sample.app.service.impl.CustomUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "Endpoints to handle registration and token generation", tags = "Authentication")
public class JwtController {

	Logger logger = LoggerFactory.getLogger(JwtController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(method = RequestMethod.POST, value = "/token")
	@ApiOperation(value = "Generate token",
		notes = "provide username and password to get the token"
	)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch(UsernameNotFoundException e) {
			logger.error(e.getMessage());
			throw new Exception("bad credentials");
		}
		UserDetails userDetails;
		try {
			userDetails = this.customUserDetailsService.loadUserByUsername(
					jwtRequest.getUsername());
		} catch(Exception e) {
			throw new Exception("user not found !");
		}
		String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	@ApiOperation(value = "User registration",
		notes = "provide username and password of your choice to register"
	)
	public ResponseEntity<String> register(@RequestBody JwtRequest jwtRequest) {
		if(userRepo.addUser(jwtRequest.getUsername(), jwtRequest.getPassword()) >= 1) {
			return new ResponseEntity<String>("User registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid request body", HttpStatus.BAD_REQUEST);
		}
	}
	
}
