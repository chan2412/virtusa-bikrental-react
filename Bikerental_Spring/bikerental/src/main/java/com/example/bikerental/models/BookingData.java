package com.example.bikerental.models;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
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
}
