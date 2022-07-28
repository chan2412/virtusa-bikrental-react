package com.example.bikerental.Models;


import lombok.Data;

@Data
public class BikeData {
	private String bikeid;
	private String bikeno;
	private String adminid;
	private String status;
	private String bikeimageurl;
	private String price;
	private String model;
	private String type;
	
	public BikeData() {
		super();
	}
	public BikeData(String bikeid, String bikeno, String adminid, String status, String bikeimageurl, String price,
			String model, String type) {
		super();
		this.bikeid = bikeid;
		this.bikeno = bikeno;
		this.adminid = adminid;
		this.status = status;
		this.bikeimageurl = bikeimageurl;
		this.price = price;
		this.model = model;
		this.type = type;
	}
	public String getBikeid() {
		return bikeid;
	}
	public void setBikeid(String bikeid) {
		this.bikeid = bikeid;
	}
	public String getBikeno() {
		return bikeno;
	}
	public void setBikeno(String bikeno) {
		this.bikeno = bikeno;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBikeimageurl() {
		return bikeimageurl;
	}
	public void setBikeimageurl(String bikeimageurl) {
		this.bikeimageurl = bikeimageurl;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
