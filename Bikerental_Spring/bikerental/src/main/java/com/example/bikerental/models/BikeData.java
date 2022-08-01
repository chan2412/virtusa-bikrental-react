package com.example.bikerental.models;


import lombok.Getter;

@Getter
public class BikeData {
	private String bikeid;
	private String bikeno;
	private String adminid;
	private String status;
	private String bikeimageurl;
	private String price;
	private String model;
	private String type;
	public BikeData(String bikeid, String bikeno, String adminid,  String bikeimageurl, String price,
			String model, String type) {
		super();
		this.bikeid = bikeid;
		this.bikeno = bikeno;
		this.adminid = adminid;
		this.status = "available";
		this.bikeimageurl = bikeimageurl;
		this.price = price;
		this.model = model;
		this.type = type;
	}
	
	
}
