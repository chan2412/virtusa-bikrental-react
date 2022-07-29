package com.example.bikerental.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Bookingmodel {
	@Id
	String bookingid;
	@NotBlank
	String userid;
	@NotBlank
	String bikemodel;
	@NotBlank
	String adminid;
	@NotBlank
	String companyname;
	@NotBlank
	String bikeid;
	@Min(10)
	@Max(100)
	String rent;
	@Min(1)
	String days;
	@NotBlank
	String totalprice;

	public Bookingmodel(BookingData newBooking) {
		this.bookingid = newBooking.getBookingid();
		this.userid = newBooking.getUserid();
		this.bikemodel = newBooking.getBikemodel();
		this.adminid = newBooking.getAdminid();
		this.companyname = newBooking.getCompanyname();
		this.bikeid = newBooking.getBikeid();
		this.rent = newBooking.getRent();
		this.days = newBooking.getDays();
		this.totalprice = newBooking.getTotalprice();
	}

	public String getBikemodel() {
		return bikemodel;
	}

}
