package com.example.bikerental.Models;

import com.example.bikerental.Models.Admindata;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Admindata{
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

	public Admindata() {
		super();
	}

	public Admindata(String id, String email, String password, String mobilenumber, String sellername, String userrole,
			String companyname, String companyimageurl, String companyaddress, int earnings) {
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
