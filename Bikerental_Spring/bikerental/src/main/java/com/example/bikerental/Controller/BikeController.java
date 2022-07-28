package com.example.bikerental.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikerental.Models.BikeData;
import com.example.bikerental.Models.Bikemodel;
import com.example.bikerental.services.Bikeservice;

@RestController
@CrossOrigin
public class BikeController {
	Logger logger = LogManager.getLogger(BikeController.class);
	@Autowired
	private Bikeservice bikeservice;

	@GetMapping("/bikes")
	public List<Bikemodel> getbikes() {
		logger.info("Getting All Bikes");
		return bikeservice.getAllbikes();
	}

	@GetMapping("/bike/{bikeid}")
	public Bikemodel getbike(@PathVariable String bikeid) {
		logger.info("Getting Bike with id {}" , bikeid);
		return bikeservice.getBikeById(bikeid);
	}
	
	@GetMapping("/bike/admin/{adminid}")
	public List<Bikemodel> getbikesbyid(@PathVariable String adminid) {
		logger.info("Getting Bikes with admin id {}"  ,adminid);
		return bikeservice.getBikesByCompany(adminid);
	}
	
	@PostMapping("/bike/add")
	public String savebike(@RequestBody BikeData newbike) {
			return bikeservice.addbike(newbike);
	}

	@GetMapping("/bike/book/{bikeId}")
	public String bookbike(@PathVariable String bikeId) {
		return bikeservice.bookbike(bikeId);
	}

	@PutMapping("/bike/edit")
	public String editbike(@RequestBody BikeData updatedbike) {
			return bikeservice.editbike(updatedbike);
	}

	@DeleteMapping("/bike/delete/{id}")
	public String deletebike(@PathVariable String id) {
			return bikeservice.deletebike(id);
	}
}
