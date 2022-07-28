package com.example.bikerental.Controller;

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

import com.example.bikerental.Models.Admindata;
import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.services.Adminservice;

@RestController
@CrossOrigin(origins="*")
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
	adminservice.editAdmin(data);
	return ResponseEntity.ok("Admin Updated successfully");
}
 
@GetMapping("/admin/{id}")
public Adminmodel getAdmin(@PathVariable String id)
{ logger.info("Getting Admin with email id {}",id);
	return adminservice.getAdmin(id);
}
}

