package com.example.bikerental.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingData {
	 
	    String bookingid;
	    String userid;
	    String bikemodel;
	    String adminid;
	    String companyname;
	    String bikeid;
	    String rent;
	    String days;
	    String totalprice;
	    
		public BookingData() {
			super();
		}
		public BookingData(String bookingid, String userid, String bikemodel, String adminid, String companyname,
				String bikeid, String rent, String days, String totalprice) {
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
		public String getAdminid() {
			return adminid;
		}
		public void setAdminid(String adminid) {
			this.adminid = adminid;
		}
		public String getCompanyname() {
			return companyname;
		}
		public void setCompanyname(String companyname) {
			this.companyname = companyname;
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
