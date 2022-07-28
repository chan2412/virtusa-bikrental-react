package com.example.bikerental.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.Models.Bookingmodel;
import com.example.bikerental.services.Adminservice;
import com.example.bikerental.services.SuperAdminservice;

@RestController
@CrossOrigin
public class SuperAdmincontroller {
	Logger logger = LogManager.getLogger(SuperAdmincontroller.class);
    @Autowired
    private SuperAdminservice superAdminservice;
    @Autowired
    private Adminservice adminService;
    @GetMapping("/superadmin/admins")
    public List<Adminmodel> getAllAdmins()
    { logger.info("Getting All Admins");
        return adminService.getAllAdmins();
    }
    @GetMapping("/superadmin/bookings")
    public List<Bookingmodel> getAllBookings()
    {logger.info("Getting All Bookings");
        return superAdminservice.getAllBookings();
    } 
    @DeleteMapping("/superadmin/admin/{id}")
    public String deleteAdmin(@PathVariable String id)
    {
         return superAdminservice.deleteAdmin(id);
    }
}
