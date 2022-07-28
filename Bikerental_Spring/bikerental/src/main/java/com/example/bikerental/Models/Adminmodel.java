package com.example.bikerental.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
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

	public Adminmodel() {
		super();
	}

	public Adminmodel(String id, @Email(message = "Invalid Email") String email,
			@NotBlank(message = "Invalid Password") String password,
			@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered") String mobilenumber,
			@NotBlank(message = "Seller name must not be empty") String sellername, String userrole,
			@NotBlank(message = "Company name must not be empty") String companyname,
			@URL(message = "invalid url") String companyimageurl,
			@NotBlank(message = "Company address must not be empty") String companyaddress, int earnings) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.mobilenumber = mobilenumber;
		this.sellername = sellername;
		this.userrole = userrole;
		this.companyname = companyname;
		this.companyimageurl = companyimageurl;
		this.companyaddress = companyaddress;
		this.earnings = earnings;
	}

	public Adminmodel(Admindata data) {
		this.id = data.getId();
		this.email = data.getEmail();
		this.password =data.getPassword();
		this.mobilenumber = data.getMobilenumber();
		this.sellername = data.getSellername();
		this.userrole = data.getUserrole();
		this.companyname = data.getCompanyname();
		this.companyimageurl = data.getCompanyimageurl();
		this.companyaddress = data.getCompanyaddress();
		this.earnings = data.getEarnings();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanyimageurl() {
		return companyimageurl;
	}

	public void setCompanyimageurl(String companyimageurl) {
		this.companyimageurl = companyimageurl;
	}

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public int getEarnings() {
		return earnings;
	}

	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}
}
