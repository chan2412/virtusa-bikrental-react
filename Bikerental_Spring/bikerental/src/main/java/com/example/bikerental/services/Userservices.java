package com.example.bikerental.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bikerental.Models.Loginmodel;
import com.example.bikerental.Models.Userdata;
import com.example.bikerental.Models.Usermodel;
import com.example.bikerental.Repositories.UserRepository;

@Service
public class Userservices {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	public List<Usermodel> getAll() 
	{
		List<Usermodel> users=new ArrayList<>();
		userRepository.findAll().
		forEach(users::add);
		return users;
	}
	public boolean userexist(Usermodel newuser)
	{ boolean bool=false;
		if( (userRepository.existsByEmail(newuser.getEmail())) || (userRepository.existsByUsername(newuser.getUsername())))
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
			 return "User added successfully";
		 }
		 return "User already exist";
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
	public void deleteuser(String id)
	{
		userRepository.deleteById(id);
	}
	public boolean userExists(Loginmodel login)
	{
		return userRepository.existsByEmailAndPassword(login.getEmail(), login.getPassword());
	}
}
