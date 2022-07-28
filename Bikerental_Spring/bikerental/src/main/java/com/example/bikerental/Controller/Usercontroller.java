package com.example.bikerental.Controller;


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

import com.example.bikerental.Models.Userdata;
import com.example.bikerental.Models.Usermodel;
import com.example.bikerental.services.Userservices;
@RestController
@CrossOrigin
public class Usercontroller {
	Logger logger = LogManager.getLogger(Usercontroller.class);
	@Autowired
	private Userservices userservices;
 
	@PutMapping("/user/edit")
	public ResponseEntity<String> updateuser(@RequestBody Userdata updateduser) {
		logger.debug("Editing user {}", updateduser.getUsername());
		return ResponseEntity.ok(userservices.edituser(updateduser));
	}
	@GetMapping("/user/{id}")
public Usermodel getUser(@PathVariable String id)
{
	return userservices.getUser(id);
}
}

