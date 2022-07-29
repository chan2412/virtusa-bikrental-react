package com.example.bikerental.models;

import javax.validation.constraints.NotBlank;


public class Loginmodel {
	@NotBlank(message="Email must not be empty")
private String email;
	@NotBlank(message="Password must not be empty")
private String password;
public Loginmodel() {
	super();
}
public Loginmodel(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
