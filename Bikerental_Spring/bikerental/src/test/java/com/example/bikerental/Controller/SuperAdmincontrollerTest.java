package com.example.bikerental.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.Models.Bookingmodel;
import com.example.bikerental.services.Adminservice;
import com.example.bikerental.services.Bookingservices;
import com.example.bikerental.services.SuperAdminservice;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SuperAdmincontrollerTest {
	Logger logger = LogManager.getLogger(SuperAdmincontrollerTest.class);
	@InjectMocks
	SuperAdmincontroller superadmincontroller;
	@InjectMocks
	Admincontroller admincontroller;
	@InjectMocks
	Bookingcontroller bookingcontroller;
	@Mock
	Bookingservices bookingservices;
	@Mock
	SuperAdminservice superAdminservice;
	@Mock
	Adminservice adminservice;
	@Test
	void testGetAllAdmins() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Adminmodel> admins= new ArrayList<>();
		Adminmodel RECORD_1 = new Adminmodel("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
	    Adminmodel RECORD_2 = new Adminmodel("abce","1234@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);
		admins.add(RECORD_1);
		admins.add(RECORD_2);
		when(adminservice.getAllAdmins()).thenReturn(admins);
		ResponseEntity<List<Adminmodel>> result=admincontroller.getAdmins();
	    assertThat(result.getBody()).contains(RECORD_1).hasSize(2);
	    assertThat(result.getBody().get(0)).isEqualTo(RECORD_1);
	    assertThat(result.getBody().get(1)).isEqualTo(RECORD_2);
	}

	@Test
	void testGetAllBookings() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Bookingmodel> bookings= new ArrayList<>();
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		Bookingmodel booking2=new Bookingmodel("2", "user1", "activa 5g", "admin@gmail.com", "company","2", "15", "2", "300");
		bookings.add(booking1);
		bookings.add(booking2);
		when(superAdminservice.getAllBookings()).thenReturn(bookings);
		List<Bookingmodel> result=superadmincontroller.getAllBookings();
	    assertThat(result).contains(booking1).hasSize(2);
	    assertThat(result.get(0).getBikemodel()).isEqualTo(booking1.getBikemodel());
	    assertThat(result.get(1).getBikemodel()).isEqualTo(booking2.getBikemodel());
	}

	@Test
	void testDeleteAdmin() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(superAdminservice.deleteAdmin("admin@gmail.com")).thenReturn("Admin Deleted Successfully");
		String result=superadmincontroller.deleteAdmin("admin@gmail.com");
	    assertThat(result).isEqualTo("Admin Deleted Successfully");
	}

}