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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.bikerental.Models.BikeData;
import com.example.bikerental.Models.Bikemodel;
import com.example.bikerental.services.Bikeservice;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class BikeControllerTest {
	Logger logger = LogManager.getLogger(AuthcontrollerTest.class);
	@InjectMocks
	BikeController bikecontroller;

	@Mock
	Bikeservice bikeservice;
	
	@Test
	void testGetbikes() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Bikemodel> bikes= new ArrayList<>();
		Bikemodel bike1=new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		Bikemodel bike2=new Bikemodel("abce","TN 11 AB 2222", "admin1@gmail.com","available","https://picsum.photos/200/200", "15", "Activa 5G", "scooty");
		bikes.add(bike1);
		bikes.add(bike2);
		when(bikeservice.getAllbikes()).thenReturn(bikes);
		List<Bikemodel> result=bikecontroller.getbikes();
	    assertThat(result).contains(bike1).hasSize(2);
	    assertThat(result.get(0).getModel()).isEqualTo(bike1.getModel());
	    assertThat(result.get(1).getModel()).isEqualTo(bike2.getModel());
	}

	@Test
	void testGetbike() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Bikemodel bike1=new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com", "available","https://picsum.photos/200/200","20", "R15", "bike");
		when(bikeservice.getBikeById(bike1.getBikeid())).thenReturn(bike1);
		Bikemodel res=bikecontroller.getbike("abcd");
		assertThat(res).isEqualTo(bike1);
	}

	@Test
	void testGetbikesbyid() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Bikemodel bike1=new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		List<Bikemodel> bikelist=new ArrayList<>();
		bikelist.add(bike1);
		when(bikeservice.getBikesByCompany(bike1.getAdminid())).thenReturn(bikelist);
		List<Bikemodel> res=bikecontroller.getbikesbyid("admin@gmail.com");
		assertThat(res.get(0)).isEqualTo(bike1);
	}

	@Test
	void testSavebike() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		BikeData bike1=new BikeData("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		when(bikeservice.addbike(bike1)).thenReturn("Bike Added Successfully");
		String res=bikecontroller.savebike(bike1);
		assertThat(res).isEqualTo("Bike Added Successfully");
	}

	@Test
	void testBookbike() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(bikeservice.bookbike("abcd")).thenReturn("Bike Booked Successfully");
		String res=bikecontroller.bookbike("abcd");
		assertThat(res).isEqualTo("Bike Booked Successfully");
	}

	@Test
	void testEditbike() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		BikeData bike1=new BikeData("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		when(bikeservice.editbike(bike1)).thenReturn("Bike Updated Successfully");
		String res=bikecontroller.editbike(bike1);
		assertThat(res).isEqualTo("Bike Updated Successfully");
	}

	@Test
	void testDeletebike() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(bikeservice.deletebike("abcd")).thenReturn("Bike Deleted Successfully");
		String res=bikecontroller.deletebike("abcd");
		assertThat(res).isEqualTo("Bike Deleted Successfully");
	}

}
