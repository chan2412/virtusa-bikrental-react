package com.example.bikerental.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bikerental.models.Loginmodel;
import com.example.bikerental.models.Userdata;
import com.example.bikerental.models.Usermodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.BookingRepository;
import com.example.bikerental.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserservicesTest {
	Logger logger = LogManager.getLogger(BikeserviceTest.class);
	@InjectMocks
	Userservices userservices;
	@Mock
	UserRepository userRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	BookingRepository bookingRepository;
	@Mock
	BCryptPasswordEncoder passwordEncoder;

	@Test
	void testUserexist() {
		Usermodel user= new Usermodel("user@gmail.com", "user", "user", "1234567890", 20, "user");
		given(userRepository.existsByEmail("user@gmail.com")).willReturn(true);
		given(userRepository.existsByUsername("user")).willReturn(true);
		boolean res=userservices.userexist(user);
		assertEquals(true, res);
	}
	@Test
	void testUserexist1() {
		Usermodel user= new Usermodel("user1@gmail.com", "user", "user1", "1234567890", 20, "user");
		boolean res=userservices.userexist(user);
		assertEquals(false, res);
	}

	@Test
	void testUserExistsByEmail() {
		given(userRepository.existsByEmail("user@gmail.com")).willReturn(true);
		boolean res=userservices.userExistsByEmail("user@gmail.com");
		assertEquals(true, res);
	}

	@Test
	void testUserExistsByEmail1() {
		boolean res=userservices.userExistsByEmail("user1@gmail.com");
		assertEquals(false, res);
	}

	@Test
	void testGetUser() {
	    Usermodel RECORD_1 =new Usermodel("user@gmail.com", "password","user", "1234567890", 25,"user");
		given(userRepository.findByUsername("user")).willReturn(RECORD_1);
    Usermodel result = userservices.getUser("user");
    assertThat(result).isEqualTo(RECORD_1);
	}

	@Test
	void testAdduser() {
		Userdata user= new Userdata("user@gmail.com", "user", "user", "1234567890", 20, "user");
		given(userRepository.existsByEmail("user@gmail.com")).willReturn(false);
		given(userRepository.existsByUsername("user")).willReturn(false);
		String res=userservices.adduser(user);
		assertThat(res).isEqualTo("User Added Successfully");
	}
	@Test
	void testAdduser1() {
		Userdata user= new Userdata("user1@gmail.com", "user", "user1", "1234567890", 20, "user");
		given(userRepository.existsByEmail("user1@gmail.com")).willReturn(true);
		String res=userservices.adduser(user);
		assertThat(res).isEqualTo("User Already Exist");
	}

	@Test
	void testEdituser() {
		Userdata user= new Userdata("user@gmail.com", "user", "user", "1234567890", 20, "user");
		given(userRepository.existsByUsername("user")).willReturn(true);
		String res=userservices.edituser(user);
		assertThat(res).isEqualTo("User Updated Successfully");
	}
	@Test
	void testEdituser1() {
		Userdata user= new Userdata("user1@gmail.com", "user", "user1", "1234567890", 20, "user");
		String res=userservices.edituser(user);
		assertThat(res).isEqualTo("User Not Exist");
	}

	@Test
	void testDeleteuser() {
		given(userRepository.existsByUsername("user")).willReturn(true);
		String res=userservices.deleteuser("user");
		assertEquals("User Deleted Successfully", res);
	}
	@Test
	void testDeleteuser1() {
		String res=userservices.deleteuser("user1");
		assertEquals("User Not Exist", res);
	}

	@Test
	void testUserExists() {
		Loginmodel loginmodel=new Loginmodel("user","user");
		given(userRepository.existsByEmailAndPassword("user","user")).willReturn(true);
		assertTrue(userservices.userExists(loginmodel));
	}

	@Test
	void testUserExists1() {
		Loginmodel loginmodel=new Loginmodel("user","pass");
		assertFalse(userservices.userExists(loginmodel));
	}
}
