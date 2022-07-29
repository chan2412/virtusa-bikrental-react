package com.example.bikerental.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Usermodel {
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

	public Usermodel(String email, @NotBlank(message = "Invalid Password") String password,
			@NotBlank(message = "Invalid Username") String username,
			@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered") String mobilenumber,
			@Min(value = 18, message = "Invalid age") @Max(value = 70, message = "Invalid age") int age,
			@NotBlank String userrole) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.mobilenumber = mobilenumber;
		this.age = age;
		this.userrole = userrole;
	}

	public Usermodel(Userdata updateduser) {
		super();
		this.email = updateduser.getEmail();
		this.password = updateduser.getPassword();
		this.username = updateduser.getUsername();
		this.mobilenumber = updateduser.getMobilenumber();
		this.age = updateduser.getAge();
		this.userrole = updateduser.getUserrole();
	}

	public String getEmail() {
		return email;
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

}
