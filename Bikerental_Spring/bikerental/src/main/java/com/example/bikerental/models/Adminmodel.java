package com.example.bikerental.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Entity
@AllArgsConstructor
@Getter
public class Adminmodel {
	@Id
	private String id;
	@Email(message = "Invalid Email")
	private String email;
	@NotBlank(message = "Invalid Password")
	private String password;
	@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered")
	private String mobilenumber;
	@NotBlank(message = "Seller name must not be empty")
	private String sellername;
	private String userrole;
	@NotBlank(message = "Company name must not be empty")
	private String companyname;
	@URL(message = "invalid url")
	private String companyimageurl;
	@NotBlank(message = "Company address must not be empty")
	private String companyaddress;
	private int earnings;
	public Adminmodel(Admindata data) {
		this.id = data.getId();
		this.email = data.getEmail();
		this.password = data.getPassword();
		this.mobilenumber = data.getMobilenumber();
		this.sellername = data.getSellername();
		this.userrole = data.getUserrole();
		this.companyname = data.getCompanyname();
		this.companyimageurl = data.getCompanyimageurl();
		this.companyaddress = data.getCompanyaddress();
		this.earnings = data.getEarnings();
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
