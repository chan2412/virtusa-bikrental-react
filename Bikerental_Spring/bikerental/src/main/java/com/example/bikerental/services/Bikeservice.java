package com.example.bikerental.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bikerental.Models.BikeData;
import com.example.bikerental.Models.Bikemodel;
import com.example.bikerental.Repositories.AdminRepository;
import com.example.bikerental.Repositories.BikeRepository;

@Service
public class Bikeservice {
	Logger logger = LogManager.getLogger(Bikeservice.class);
	@Autowired
	private BikeRepository bikeRepository;
	@Autowired
	private AdminRepository adminRepository;
	String invalidBikeString = "Bike Not Exists";

	public List<Bikemodel> getAllbikes() {
		List<Bikemodel> bikes = new ArrayList<>();
		bikeRepository.findAll().forEach(bikes::add);
		return bikes;
	}

	public List<Bikemodel> getBikesByCompany(String email) {
		List<Bikemodel> bikes = new ArrayList<>();
		List<Bikemodel> bikesbyid;
		bikeRepository.findAll().forEach(bikes::add);
		bikesbyid = bikes.stream().filter(e -> e.getAdminid().equals(email)).toList();
		return bikesbyid;
	}

	public Bikemodel getBikeById(String bikeid) {
		Optional<Bikemodel> value = bikeRepository.findById(bikeid);
		if (value.isPresent()) {
			return value.get();
		}
		return null;
	}

	public boolean bikeexist(BikeData newbike) {
		boolean bool;
		if ((bikeRepository.existsById(newbike.getBikeid())) || (bikeRepository.existsByBikeno(newbike.getBikeno()))) {
			bool = true;
		} else {
			bool = false;
		}
		return bool;
	}

	public boolean bikeexistbyId(String id) {
		boolean bool;
		if (bikeRepository.existsById(id)) {
			bool = true;
		} else {
			bool = false;
		}
		return bool;
	}

	public String addbike(BikeData data) {
		Bikemodel newbike = new Bikemodel(data);

		if (!bikeexist(data)) {
			String email = newbike.getAdminid();
			if ((adminRepository.existsByEmail(email))) {
				bikeRepository.save(newbike);
				logger.info("New Bike Added Successfully");
				return "Bike Added Successfully";
			} else {
				return "Admin Not Exist";
			}
		} else {
			logger.error("Trying to add a bike But already exist");
			return "Bike Already Exists";
		}

	}

	public String bookbike(String bikeid) {
		if (bikeRepository.existsById(bikeid)) {
			Optional<Bikemodel> value = bikeRepository.findById(bikeid);
			if (value.isPresent()) {
				Bikemodel bikemodel = value.get();
				bikemodel.setStatus("not available");
				bikeRepository.save(bikemodel);
				return "Bike Booked Successfully";
			} else {
				return invalidBikeString;
			}
		} else {
			return invalidBikeString;
		}
	}

	public String editbike(BikeData data) {
		if (!bikeRepository.existsById(data.getBikeid())) {
			logger.error("Trying to update a bike But Not exist");
			return invalidBikeString;
		} else {
			Bikemodel updatedbike = new Bikemodel(data);
			bikeRepository.save(updatedbike);
			logger.info("Bike with id {}{}", updatedbike.getBikeid(), " Updated Successfully");
			return "Bike Updated Successfully";
		}
	}

	public String deletebike(String id) {
		if (bikeRepository.existsById(id)) {
			bikeRepository.deleteById(id);
			logger.info("Bike with id {}{}", id, " Deleted Successfully");
			return "Bike Deleted Successfully";
		} else {
			logger.error("Trying to delete Bike with id {}{}", id, " but not exist");
			return invalidBikeString;
		}
	}
}
