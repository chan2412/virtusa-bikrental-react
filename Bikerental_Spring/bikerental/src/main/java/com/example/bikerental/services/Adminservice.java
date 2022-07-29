package com.example.bikerental.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bikerental.models.Admindata;
import com.example.bikerental.models.Adminmodel;
import com.example.bikerental.models.Loginmodel;
import com.example.bikerental.repositories.AdminRepository;

@Service
public class Adminservice {
	Logger logger = LogManager.getLogger(Adminservice.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AdminRepository adminRepository;

	public List<Adminmodel> getAllAdmins() {
		List<Adminmodel> admins = new ArrayList<>();
		adminRepository.findAll().forEach(admins::add);
		return admins;
	}

	public String addAdmin(Admindata data) {
		Adminmodel newadmin = new Adminmodel(data);
		String email = newadmin.getEmail();
		String password = newadmin.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		newadmin.setPassword(encodedPassword);
		if (!adminRepository.existsByEmail(email)) {
			adminRepository.save(newadmin);
			logger.info("Admin with email {}{}", newadmin.getEmail(), " added successfully");
			return "Admin Added Successfully";
		}
		logger.error("Trying to Register Admin with email {}{}", newadmin.getEmail(), " but already exist");
		return "Admin Already Exist";
	}

	public String editAdmin(Admindata updateddata) {
		Adminmodel updatedadmin = new Adminmodel(updateddata);
		if (adminRepository.existsByEmail(updatedadmin.getEmail())) {
			adminRepository.save(updatedadmin);
			return "Admin Updated Successfully";
		}
		return "Admin Not Exist";
	}

public Object getAdmin(String email)
{if(adminRepository.existsByEmail(email)) {
return adminRepository.findByEmail(email);
}
else {
	return "Admin Not Exist";
}
}

	public boolean adminExists(Loginmodel login) {
		return adminRepository.existsByEmailAndPassword(login.getEmail(), login.getPassword());
	}

	public boolean adminExistsByEmail(String email) {
		return adminRepository.existsByEmail(email);
	}
}
