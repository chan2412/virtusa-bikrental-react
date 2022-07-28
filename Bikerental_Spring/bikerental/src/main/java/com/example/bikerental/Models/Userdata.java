package com.example.bikerental.Models;


import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Userdata{

	@Id
	private String email;
	@NotBlank(message="Invalid Password")
	private String password;
	@NotBlank(message="Invalid Username")
	private String username;
	@Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered")
	private String mobilenumber;
	@Min(value=18, message="Invalid age")
	@Max(value=70, message="Invalid age")
	private int age;
	@NotBlank
	private String userrole;
public Userdata() {
	super();
}
public Userdata(String email, String password, String username, String mobilenumber, int age, String userrole) {
	super();
	this.email = email;
	this.password = password;
	this.username = username;
	this.mobilenumber = mobilenumber;
	this.age = age;
	this.userrole = userrole;
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
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getMobilenumber() {
	return mobilenumber;
}
public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getUserrole() {
	return userrole;
}
public void setUserrole(String userrole) {
	this.userrole = userrole;
}


}