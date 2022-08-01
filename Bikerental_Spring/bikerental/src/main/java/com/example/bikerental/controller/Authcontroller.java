package com.example.bikerental.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikerental.models.Admindata;
import com.example.bikerental.models.Adminmodel;
import com.example.bikerental.models.AuthenticationRequest;
import com.example.bikerental.models.AuthenticationResponse;
import com.example.bikerental.models.SuperAdminmodel;
import com.example.bikerental.models.Userdata;
import com.example.bikerental.models.Usermodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.SuperAdminRepository;
import com.example.bikerental.repositories.UserRepository;
import com.example.bikerental.services.Adminservice;
import com.example.bikerental.services.MyUserDetailsService;
import com.example.bikerental.services.Userservices;
import com.example.bikerental.utils.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Authcontroller {
	Logger logger = LogManager.getLogger(Authcontroller.class);
	@Autowired
	private Userservices userservices;
	@Autowired
	private Adminservice adminservice;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	SuperAdminRepository superAdminRepository;

	@PostMapping("/user/register")
	public String adduser(@RequestBody Userdata newuser) {
		return userservices.adduser(newuser);
	}

	@PostMapping("/admin/register")
	public String addadmin(@RequestBody Admindata newadmin) {
		return adminservice.addAdmin(newadmin);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Usermodel user = userRepository.findByUsername(authenticationRequest.getUsername());
		Adminmodel admin = adminRepository.findByEmail(authenticationRequest.getUsername());
		SuperAdminmodel superadmin = superAdminRepository.findByEmail(authenticationRequest.getUsername());
		String userid;
		String userrole;
		if (user == null) {
			if (admin == null) {
				userid = superadmin.getEmail();
				userrole = "superadmin";
				logger.info(superadmin);
			} else {
				userid = admin.getEmail();
				userrole = "admin";
				logger.info(admin);
			}
		} else {
			userid = user.getUsername();
			userrole = "user";
			logger.info(user);
		}

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, userid, userrole));
	}
}
