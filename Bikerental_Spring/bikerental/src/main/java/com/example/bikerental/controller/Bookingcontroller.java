package com.example.bikerental.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikerental.models.BookingData;
import com.example.bikerental.models.Bookingmodel;
import com.example.bikerental.services.Bookingservices;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class Bookingcontroller {
	Logger logger = LoggerFactory.getLogger(Bookingcontroller.class);
	@Autowired
	private Bookingservices bookingservices;

	@GetMapping("/bookings/{adminid}")
	public List<Bookingmodel> getAllBookings(@PathVariable String adminid) {
		logger.info("Getting all bookings associated with admin with email id {}", adminid);
		return bookingservices.getAllBookings(adminid);
	}

	@GetMapping("/mybookings/{userid}")
	public List<Bookingmodel> getMyBookings(@PathVariable String userid) {
		logger.info("Getting all bookings by user with id {}", userid);
		return bookingservices.getMyBookings(userid);
	}

	@GetMapping("/booking/{id}")
	public Object getBooking(@PathVariable String id) {
		logger.info("Getting Details of the booking with id {}", id);
		return bookingservices.getBooking(id);
	}
 
	@PostMapping("/booking/add") 
	public String addBooking(@RequestBody BookingData newbooking) {
		
		logger.info("Bike with id {}{}{}{}", newbooking.getBikeid(), " is booked by user ", newbooking.getUserid(),
				" Successfully");
		return bookingservices.addBooking(newbooking);
	}

	@DeleteMapping("/booking/{id}")
	public String cancelBooking(@PathVariable String id) {
		logger.info("Booking with id {}{}", id, " is deleted Successfully");
		return bookingservices.cancelBooking(id);
	}
}
