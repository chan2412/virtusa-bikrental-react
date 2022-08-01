package com.example.bikerental.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.bikerental.controller.Authcontroller;
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

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AuthcontrollerTest {
	Logger logger = LogManager.getLogger(AuthcontrollerTest.class);
	@InjectMocks
	Authcontroller authcontroller;
	@Mock
	AuthenticationManager authenticationManager;
	@Mock
	UserRepository userRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	SuperAdminRepository superAdminRepository;
	@Mock
	Userservices userservices;
	@Mock
	MyUserDetailsService myUserDetailsService;
	@Mock
	Adminservice adminservice;
	@Mock
	JwtUtil jwtTokenUtil;
	@Test
	void testAuthRequest() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("testusername", "test");
		assertThat(authenticationRequest.getUsername()).isEqualTo("testusername");
		assertThat(authenticationRequest.getPassword()).isEqualTo("test");
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("jwt", "test", "test");
		assertThat(authenticationResponse.jwt()).isEqualTo("jwt");
		assertThat(authenticationResponse.userid()).isEqualTo("test");
		assertThat(authenticationResponse.userrole()).isEqualTo("test");
	}

	@Test
	void testAdduser() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		logger.info(userservices);
		when(userservices.adduser(any(Userdata.class))).thenReturn("User added successfully");
		Userdata RECORD_1 = new Userdata("user@gmail.com", "password", "user", "1234567890", 25, "user");
		String result = authcontroller.adduser(RECORD_1);
		assertThat(result).isEqualTo("User added successfully");
	}

	@Test
	void testAddadmin1() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(adminservice.addAdmin(any(Admindata.class))).thenReturn("Admin added successfully");
		Admindata RECORD_1 = new Admindata("abcd", "123@gmail.com", "password", "1234567890", "Cebu Philippines",
				"cebu", "admin", "https://picsum.photos/200/200", "123,asdf", 0);
		String result = authcontroller.addadmin(RECORD_1);
		assertThat(result).isEqualTo("Admin added successfully");

	}

	@Test
	void testAddadmin2() {
		when(adminservice.addAdmin(any(Admindata.class))).thenReturn("Admin already exists");
		Admindata RECORD_1 = new Admindata("abcd", "123@gmail.com", "password", "1234567890", "Cebu Philippines",
				"cebu", "admin", "https://picsum.photos/200/200", "123,asdf", 0);
		String result = authcontroller.addadmin(RECORD_1);
		assertThat(result).isEqualTo("Admin already exists");

	}


	@Test
	void testCreateAuthenticationToken() {
		when(myUserDetailsService.loadUserByUsername("user")).thenReturn(new User("user","user", new ArrayList<>()));
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("user", "user");
		Usermodel usermodel=new Usermodel("user@gmail.com", "password", "user", "1234567890", 25, "user");
		when(userRepository.findByUsername("user")).thenReturn(usermodel);
		ResponseEntity<AuthenticationResponse> res=authcontroller.createAuthenticationToken(authenticationRequest);
		logger.info(res.getBody());
		assertThat(res.getBody().userid()).isEqualTo("user");
 	}
	@Test
	void testCreateAuthenticationToken1() {
		when(myUserDetailsService.loadUserByUsername("admin@gmail.com")).thenReturn(new User("admin@gmail.com","admin", new ArrayList<>()));
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("admin@gmail.com", "admin");
		Adminmodel adminmodel=new Adminmodel("abcd", "admin@gmail.com", "password", "1234567890", "Cebu Philippines",
				"cebu", "admin", "https://picsum.photos/200/200", "123,asdf", 0);
		when(adminRepository.findByEmail("admin@gmail.com")).thenReturn(adminmodel);
		ResponseEntity<AuthenticationResponse> res=authcontroller.createAuthenticationToken(authenticationRequest);
		logger.info(res.getBody());
		assertThat(res.getBody().userid()).isEqualTo("admin@gmail.com");
 	}
	@Test
	void testCreateAuthenticationToken2() {
		when(myUserDetailsService.loadUserByUsername("superadmin@gmail.com")).thenReturn(new User("superadmin@gmail.com","admin", new ArrayList<>()));
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("superadmin@gmail.com", "admin");
		SuperAdminmodel superAdminmodel=new SuperAdminmodel("superadmin@gmail.com", "admin", "superadmin");
		when(superAdminRepository.findByEmail("superadmin@gmail.com")).thenReturn(superAdminmodel);
		ResponseEntity<AuthenticationResponse> res=authcontroller.createAuthenticationToken(authenticationRequest);
		logger.info(res.getBody());
		assertThat(res.getBody().userid()).isEqualTo("superadmin@gmail.com");
 	}
	@Test
	void testCreateAuthenticationToken3() {
		try {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("superadmi1n@gmail.com", "admin");
		when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()))).thenThrow(BadCredentialsException.class);
		ResponseEntity<AuthenticationResponse> res=authcontroller.createAuthenticationToken(authenticationRequest);
		logger.info(res);
		}
		catch(Exception e)
		{ 
		assertThat(e.getMessage()).isEqualTo("Incorrect username or password");
		}
	}
}
