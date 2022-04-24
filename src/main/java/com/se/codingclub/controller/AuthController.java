package com.se.codingclub.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.dto.Auth;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
	private Auth tokenWrapper;
	
	@PostMapping("/login")
	public Object loginUser(@RequestBody User user) {
		User userCheck = userService.getUserByUsername(user.getUserName());
		if(userCheck == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("username not exists"));
		}else {
			if(authService.matches(user.getPassword(), userCheck.getPassword()) == true) {
				String token = authService.generateTokenLogin(user.getUserName(),userCheck.getRole(),userCheck.getId());
				return ResponseEntity.status(200).body(new Auth(token,userCheck.getRole()));
			}else {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Incorrect password"));
			}
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponeMessage> registerUser(@RequestBody User user) {
		User userCheck = userService.getUserByUsername(user.getUserName());
		if(userCheck != null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("username exists"));
		}
		Date created_at = new Date();
		String hashPassword = authService.encode(user.getPassword());
		User newUser = new User(user.getUserName(), hashPassword, user.getFirstName(), user.getLastName(), user.getAddress(), user.getTelephone(),"customer", created_at, created_at);
		try {
			userService.createUser(newUser);
			return ResponseEntity.status(200).body(new ResponeMessage("Create user success!"));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Error!"));
		}
	}
	
	@GetMapping("/test")
	public User test() {
		String token = tokenWrapper.getToken();
		return authService.getUserByToken(token);
	}
}

