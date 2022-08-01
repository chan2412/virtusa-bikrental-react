package com.example.bikerental.models;


public class AuthenticationRequest {

	private String username;
	private String password;
	
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
