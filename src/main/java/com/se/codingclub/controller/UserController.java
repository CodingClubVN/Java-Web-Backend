package com.se.codingclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.codingclub.dto.Auth;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
	private Auth tokenWrapper;
	
	@PutMapping("/me")
	public Object updateUser(@RequestBody User user_req){
		String token = tokenWrapper.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			User user = authService.getUserByToken(token);
			User userOld = userService.updateUser(user.getId(), user_req);
			return ResponseEntity.status(200).body(userOld);
		}
	}
}
