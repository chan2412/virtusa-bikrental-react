package com.example.bikerental.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bikerental.models.Loginmodel;
import com.example.bikerental.models.Userdata;
import com.example.bikerental.models.Usermodel;
import com.example.bikerental.repositories.UserRepository;

@Service
public class Userservices {
	Logger logger = LogManager.getLogger(Userservices.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	public boolean userexist(Usermodel newuser)
	{ boolean bool=false;
		if( (userRepository.existsByEmail(newuser.getEmail())) && (userRepository.existsByUsername(newuser.getUsername())))
	{ 
		bool= true;
	}
	return bool;
	}
	public boolean userExistsByEmail(String email)
	{
		return userRepository.existsByEmail(email);
	}
	public Usermodel getUser(String username)
	{
		return userRepository.findByUsername(username);
	}
	public String adduser(Userdata data) {
		Usermodel newUser=new Usermodel(data);
		String email=newUser.getEmail();
		String password=newUser.getPassword();
		String encodedPassword=passwordEncoder.encode(password);
		newUser.setPassword(encodedPassword);
		 if(!userRepository.existsByEmail(email) && !userRepository.existsByUsername(newUser.getUsername()) )
		 { userRepository.save(newUser);
		 logger.error("Trying to Register User with email {}{}" , newUser.getEmail() ," but already exist");
			 return "User Added Successfully";
		 }
		 logger.info("User with email {}{}" , newUser.getEmail() , " added successfully");
		 return "User Already Exist";
		}
	
	public String edituser(Userdata data) {
		if(userRepository.existsByUsername(data.getUsername())) {
		Usermodel updateduser=new Usermodel(data);
		userRepository.save(updateduser);
		return "User Updated Successfully";
		}
		else {
			return "User Not Exist";
		}
	}
	public String deleteuser(String id)
	{if(userRepository.existsByUsername(id)){
		userRepository.deleteById(id);
		return "User Deleted Successfully";
	}
	else
	{
		return "User Not Exist";
	}
	}
	public boolean userExists(Loginmodel login)
	{
		return userRepository.existsByEmailAndPassword(login.getEmail(), login.getPassword());
	}
}
