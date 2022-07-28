package com.example.bikerental.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikerental.Models.Admindata;
import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.Models.AuthenticationRequest;
import com.example.bikerental.Models.AuthenticationResponse;
import com.example.bikerental.Models.SuperAdminmodel;
import com.example.bikerental.Models.Userdata;
import com.example.bikerental.Models.Usermodel;
import com.example.bikerental.Repositories.AdminRepository;
import com.example.bikerental.Repositories.SuperAdminRepository;
import com.example.bikerental.Repositories.UserRepository;
import com.example.bikerental.Utils.JwtUtil;
import com.example.bikerental.services.Adminservice;
import com.example.bikerental.services.MyUserDetailsService;
import com.example.bikerental.services.Userservices;

@RestController
@CrossOrigin
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

	@GetMapping("/Hello")
	public String helloWorld() {
		return "Hello World";
	}

	@PostMapping("/user/register")
	public String adduser(@RequestBody Userdata newuser) {
		if (userservices.userExistsByEmail(newuser.getEmail()) || adminservice.adminExistsByEmail(newuser.getEmail())) {
			logger.error("Trying to Register User with email {}{}" , newuser.getEmail() ," but already exist");
			return "User already exists";
		} else {
			logger.info("User with email {}{}" , newuser.getEmail() , " added successfully");
			userservices.adduser(newuser);
			return "User added successfully";
		}
	}

	@PostMapping("/admin/register")
	public String addadmin(@RequestBody Admindata newadmin) {
		if (userservices.userExistsByEmail(newadmin.getEmail()) || adminservice.adminExistsByEmail(newadmin.getEmail())) {
			logger.error("Trying to Register Admin with email {}{}" , newadmin.getEmail() , " but already exist");
			return "Admin already exists";
		} else {
			logger.info("Admin with email {}{}" ,newadmin.getEmail() , " added successfully");
			adminservice.addAdmin(newadmin);
			return "Admin added successfully";
		}
	}

	

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws BadCredentialsException {

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
