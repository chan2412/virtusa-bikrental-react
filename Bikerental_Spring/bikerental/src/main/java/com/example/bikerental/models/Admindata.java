package com.example.bikerental.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Admindata {
	private String id;
	private String email;
	private String password;
	private String mobilenumber;
	private String sellername;
	private String userrole;
	private String companyname;
	private String companyimageurl;
	private String companyaddress;
	private int earnings;
}
