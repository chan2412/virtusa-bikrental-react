package com.example.bikerental.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bikerental.models.Bookingmodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.BookingRepository;

@Service
public class SuperAdminservice {
	Logger logger = LogManager.getLogger(SuperAdminservice.class);
   
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BookingRepository bookingRepository;
 
    public List<Bookingmodel> getAllBookings()
    {List<Bookingmodel> bookings = new ArrayList<>();
		bookingRepository.findAll().forEach(bookings::add);
		return bookings;
    }
    public String deleteAdmin(String id)
    { if(adminRepository.existsByEmail(id)) {
    	logger.info("Deleting admin with id {}",id);
        adminRepository.deleteById(id);
        return "Admin Deleted Successfully";
    }
    else {
    	logger.error("Trying to Delete Admin with email {}{}",id," but not Exist");
    	return "Admin Not Exist";
    }
    }
}
