package com.example.bikerental.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.Models.SuperAdminmodel;
import com.example.bikerental.Models.Usermodel;
import com.example.bikerental.Repositories.AdminRepository;
import com.example.bikerental.Repositories.SuperAdminRepository;
import com.example.bikerental.Repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	Logger logger=LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private SuperAdminRepository superAdminRepository;
@Override
public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
	Usermodel usermodel=userRepository.findByUsername(userName);
	if(usermodel==null)
	{ 
		Adminmodel adminmodel=adminRepository.findByEmail(userName);
		if(adminmodel==null)
		{ 
		SuperAdminmodel superAdminmodel=superAdminRepository.findByEmail(userName);
		if(superAdminmodel==null) {
		logger.error("Login Unsuccessful Username {}{}",userName," Not Found" );
			throw new UsernameNotFoundException(userName);
		}
		else {
			logger.info("Logged in as Super Admin {}", userName);
		return new User(superAdminmodel.getEmail(),superAdminmodel.getPassword(),new ArrayList<>());
		}
		}
		else {
			logger.info("Logged in as Admin {}", userName);
		return new User(adminmodel.getEmail(), adminmodel.getPassword(), new ArrayList<>());
		}
	}
	else {
		logger.info("Logged in as Username {}", userName);
	return new User(usermodel.getUsername(), usermodel.getPassword(), new ArrayList<>());
	}
}
}
