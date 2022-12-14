package com.example.bikerental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bikerental.models.BookingData;
import com.example.bikerental.models.Bookingmodel;
import com.example.bikerental.repositories.BookingRepository;

@Service
public class Bookingservices {
    @Autowired
    private BookingRepository bookingrepository;
    
    public Object getBooking(String id) {
    	Optional<Bookingmodel> value=bookingrepository.findById(id);
		if(value.isPresent()) {
        return value.get();
		}
		return "Booking Not Exist";
    }
    public List<Bookingmodel> getAllBookings(String adminid)
    {
        return bookingrepository.findByAdminid(adminid);
    }
    public List<Bookingmodel> getMyBookings(String userid)
    {
        return bookingrepository.findByUserid(userid);
    }
    public String addBooking(BookingData newBooking)
    {Bookingmodel newbooking=new Bookingmodel(newBooking);
        bookingrepository.save(newbooking);
        return "Bike Booked Successfully";
    }
    public String cancelBooking(String id)
    { 
        bookingrepository.deleteById(id);
        return "Booking Cancelled Successfully";
    }
}
