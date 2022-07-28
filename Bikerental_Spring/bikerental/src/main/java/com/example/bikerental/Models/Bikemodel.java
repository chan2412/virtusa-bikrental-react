package com.example.bikerental.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Entity 
@Table(name="bikemodel")
public class Bikemodel {
@Id
@Column(name="bikeid")
private String bikeid;
@NotBlank(message="Bike number must not be empty")
@Column(name="bikeno")
private String bikeno;
@NotBlank
@Column(name="adminid")
private String adminid;
@NotBlank 
@Column(name="status")
private String status;
@Min(10)
@Max(100)
@Column(name="price")
private String price;
@URL(message="Invalid Url")
private String bikeimageurl;
@NotBlank(message="Bike Model must not be empty")
@Column(name="model")
private String model;
@NotBlank
@Column(name="type")
private String type;

public Bikemodel() {
	super();
}

public Bikemodel(String bikeid, @NotBlank(message = "Bike number must not be empty") String bikeno,
		@NotBlank String adminid, @NotBlank String status, @Min(10) @Max(100) String price,
		@URL(message = "Invalid Url") String bikeimageurl,
		@NotBlank(message = "Bike Model must not be empty") String model, @NotBlank String type) {
	super();
	this.bikeid = bikeid;
	this.bikeno = bikeno;
	this.adminid = adminid;
	this.status = status;
	this.price = price;
	this.bikeimageurl = bikeimageurl;
	this.model = model;
	this.type = type;
}

public Bikemodel(BikeData newBike) {
	this.bikeid = newBike.getBikeid();
	this.bikeno = newBike.getBikeno();
	this.adminid = newBike.getAdminid();
	this.bikeimageurl=newBike.getBikeimageurl();
	this.status = newBike.getStatus();
	this.price =newBike.getPrice();
	this.model = newBike.getModel();
	this.type = newBike.getType();
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
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
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getBikeid() {
	return bikeid;
}
public void setBikeid(String bikeid) {
	this.bikeid = bikeid;
}
}
