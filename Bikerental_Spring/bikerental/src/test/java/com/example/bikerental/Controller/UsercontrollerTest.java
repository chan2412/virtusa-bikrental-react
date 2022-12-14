package com.example.bikerental.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

import com.example.bikerental.controller.Usercontroller;
import com.example.bikerental.models.Userdata;
import com.example.bikerental.models.Usermodel;
import com.example.bikerental.services.Userservices;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UsercontrollerTest {
	Logger logger = LogManager.getLogger(UsercontrollerTest.class);
	@InjectMocks
    private Usercontroller usercontroller;
	
	@Mock
	Userservices userservices;
	@Test
	void testUpdateuser() {
		Userdata RECORD_1 = new Userdata("user@gmail.com", "password","user", "1234567890", 25,"user");	
		when(userservices.edituser(RECORD_1)).thenReturn("User Updated Successfully");
		ResponseEntity<String> result=usercontroller.updateuser(RECORD_1);
	    assertThat(result.getBody()).isEqualTo("User Updated Successfully");
	}

	@Test
	void testGetUser() {
		Usermodel RECORD_1 = new Usermodel("user@gmail.com", "password","user", "1234567890", 25,"user");	
		when(userservices.getUser("user")).thenReturn(RECORD_1);
		Usermodel result=usercontroller.getUser("user");
	    assertThat(result.getEmail()).isEqualTo(RECORD_1.getEmail());
	    assertThat(result).isEqualTo(RECORD_1);
	}

}
