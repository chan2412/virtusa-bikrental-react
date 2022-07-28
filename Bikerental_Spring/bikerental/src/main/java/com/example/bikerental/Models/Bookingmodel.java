package com.example.bikerental.Models;

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
    
    public Bookingmodel() {
    }
   


    public Bookingmodel(String bookingid, @NotBlank String userid, @NotBlank String bikemodel, @NotBlank String adminid,
			@NotBlank String companyname, @NotBlank String bikeid, @Min(10) @Max(100) String rent, @Min(1) String days,
			@NotBlank String totalprice) {
		super();
		this.bookingid = bookingid;
		this.userid = userid;
		this.bikemodel = bikemodel;
		this.adminid = adminid;
		this.companyname = companyname;
		this.bikeid = bikeid;
		this.rent = rent;
		this.days = days;
		this.totalprice = totalprice;
	}



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

	public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    public String getAdminid() {
        return adminid;
    }
    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }
    public String getBookingid() {
        return bookingid;
    }
    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getBikemodel() {
        return bikemodel;
    }
    public void setBikemodel(String bikemodel) {
        this.bikemodel = bikemodel;
    }
    public String getBikeid() {
        return bikeid;
    }
    public void setBikeid(String bikeid) {
        this.bikeid = bikeid;
    }
    public String getRent() {
        return rent;
    }
    public void setRent(String rent) {
        this.rent = rent;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public String getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}
