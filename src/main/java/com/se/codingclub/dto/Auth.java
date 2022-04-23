package com.se.codingclub.dto;

public class Auth {
	private String token;
	private String role;

	public Auth(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}
	public Auth() {
		// TODO Auto-generated constructor stub
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Auth [token=" + token + ", role=" + role + "]";
	}
	
}
