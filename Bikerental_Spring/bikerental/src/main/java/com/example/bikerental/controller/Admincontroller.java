package com.example.bikerental.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikerental.models.Admindata;
import com.example.bikerental.models.Adminmodel;
import com.example.bikerental.services.Adminservice;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class Admincontroller {
@Autowired
private Adminservice adminservice;
Logger logger = LogManager.getLogger(Admincontroller.class);
 
@GetMapping("/admins")
public ResponseEntity<List<Adminmodel>> getAdmins()
{
	logger.info("Getting All Admins");
	return ResponseEntity.ok(adminservice.getAllAdmins());
} 

@PutMapping("/admin/edit/")
public ResponseEntity<String> editAdmin(@RequestBody Admindata data)
{ logger.info("Editing Admin with email id {}",data.getEmail());
	return ResponseEntity.ok(adminservice.editAdmin(data));
}
 
@GetMapping("/admin/{id}")
public Object getAdmin(@PathVariable String email)
{ logger.info("Getting Admin with email id {}",email);
	return adminservice.getAdmin(email);
}
}

