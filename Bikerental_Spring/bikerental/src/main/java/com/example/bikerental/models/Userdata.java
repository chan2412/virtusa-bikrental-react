package com.example.bikerental.models;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Userdata {

	@Id
	private String email;
	@NotBlank(message = "Invalid Password")
	private String password;
	@NotBlank(message = "Invalid Username")
	private String username;
	@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered")
	private String mobilenumber;
	@Min(value = 18, message = "Invalid age")
	@Max(value = 70, message = "Invalid age")
	private int age;
	@NotBlank
	private String userrole;

	public Userdata(String email, String password, String username, String mobilenumber, int age, String userrole) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.mobilenumber = mobilenumber;
		this.age = age;
		this.userrole = userrole;
	}
}