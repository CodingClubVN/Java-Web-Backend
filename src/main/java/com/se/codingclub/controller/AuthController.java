package com.se.codingclub.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.dto.Token;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public Token loginUser(@RequestBody User user) {
		
		return new Token();
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponeMessage> registerUser(@RequestBody User user) {
		User userCheck = userService.getUserByUsername(user.getUserName());
		if(userCheck != null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("username exists"));
		}
		Date created_at = new Date();
		String hashPassword = encode(user.getPassword());
		User newUser = new User(user.getUserName(), hashPassword, user.getFirstName(), user.getLastName(), user.getAddress(), user.getTelephone(), created_at, created_at) ;
		try {
			userService.createUser(newUser);
			return ResponseEntity.ok().body(new ResponeMessage("Create user success!"));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Error!"));
		}
	}	
	public String encode(String rawPassword) {
		String salt =BCrypt.gensalt(10);
		return BCrypt.hashpw(rawPassword, salt);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
	
		return BCrypt.checkpw(rawPassword, encodedPassword);
	}
}

