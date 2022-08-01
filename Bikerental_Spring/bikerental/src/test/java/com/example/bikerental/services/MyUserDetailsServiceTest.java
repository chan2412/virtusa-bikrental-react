package com.example.bikerental.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.bikerental.models.Adminmodel;
import com.example.bikerental.models.SuperAdminmodel;
import com.example.bikerental.models.Usermodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.SuperAdminRepository;
import com.example.bikerental.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {
	Logger logger = LogManager.getLogger(BikeserviceTest.class);
	@InjectMocks
	MyUserDetailsService myUserDetailsService;
	@Mock
	UserRepository userRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	SuperAdminRepository superAdminRepository;
	@Test
	void testLoadUserByUsername() {
		SuperAdminmodel superAdminmodel=new SuperAdminmodel("superadmin@gmail.com", "admin", "superadmin");
		given(superAdminRepository.findByEmail("superadmin@gmail.com")).willReturn(superAdminmodel);
		UserDetails res=myUserDetailsService.loadUserByUsername("superadmin@gmail.com");
		assertThat(res.getUsername()).isEqualTo("superadmin@gmail.com");
	}
	@Test
	void testLoadUserByUsername1() {
		Adminmodel adminmodel=new Adminmodel("abcd", "admin@gmail.com", "password", "1234567890", "Cebu Philippines",
				"cebu", "admin", "https://picsum.photos/200/200", "123,asdf", 0);
		given(adminRepository.findByEmail("admin@gmail.com")).willReturn(adminmodel);
		UserDetails res=myUserDetailsService.loadUserByUsername("admin@gmail.com");
		assertThat(res.getUsername()).isEqualTo("admin@gmail.com");
	}
	@Test
	void testLoadUserByUsername2() {
		Usermodel usermodel=new Usermodel("user@gmail.com", "password", "user", "1234567890", 25, "user");
		given(userRepository.findByUsername("user")).willReturn(usermodel);
		UserDetails res=myUserDetailsService.loadUserByUsername("user");
		assertThat(res.getUsername()).isEqualTo("user");
	}
	@Test
	void testLoadUserByUsername3() {
		try {
		UserDetails res=myUserDetailsService.loadUserByUsername("user1");
		assertThat(res.getUsername()).isEqualTo("user1");
		}
		catch(Exception e){
			assertThat(e.getMessage()).isEqualTo("user1");
		}
	}

}
