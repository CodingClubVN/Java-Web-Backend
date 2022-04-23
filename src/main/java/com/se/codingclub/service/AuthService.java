package com.se.codingclub.service;

public interface AuthService {

	public String encode(String rawPassword);
	public boolean matches(String rawPassword, String encodedPassword);
	public String generateTokenLogin(String username, String role, int user_id);
}
