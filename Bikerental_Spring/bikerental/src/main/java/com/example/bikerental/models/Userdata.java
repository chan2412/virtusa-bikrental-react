package com.example.bikerental.models;

import lombok.Getter;

@Getter

public class Userdata {
	private String email;
	private String password;
	private String username;
	private String mobilenumber;
	private int age;
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