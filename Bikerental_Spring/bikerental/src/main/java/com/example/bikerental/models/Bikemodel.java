package com.example.bikerental.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "bikemodel")
public class Bikemodel {
	@Id
	@Column(name = "bikeid")
	private String bikeid;
	@NotBlank(message = "Bike number must not be empty")
	@Column(name = "bikeno")
	private String bikeno;
	@NotBlank
	@Column(name = "adminid")
	private String adminid;
	@NotBlank
	@Column(name = "status")
	private String status;
	@Min(10)
	@Max(100)
	@Column(name = "price")
	private String price;
	@URL(message = "Invalid Url")
	private String bikeimageurl;
	@NotBlank(message = "Bike Model must not be empty")
	@Column(name = "model")
	private String model;
	@NotBlank
	@Column(name = "type")
	private String type;

	public Bikemodel(BikeData newBike) {
		this.bikeid = newBike.getBikeid();
		this.bikeno = newBike.getBikeno();
		this.adminid = newBike.getAdminid();
		this.bikeimageurl = newBike.getBikeimageurl();
		this.status = newBike.getStatus();
		this.price = newBike.getPrice();
		this.model = newBike.getModel();
		this.type = newBike.getType();
	}

	public String getBikeid() {
		return bikeid;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModel() {
		return model;
	}

}
