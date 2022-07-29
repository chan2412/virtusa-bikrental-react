package com.example.bikerental.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bikerental.models.BikeData;
import com.example.bikerental.models.Bikemodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.BikeRepository;

@ExtendWith(MockitoExtension.class)
class BikeserviceTest {
	Logger logger = LogManager.getLogger(BikeserviceTest.class);
	@InjectMocks
	Bikeservice bikeservice;

	@Mock
	BikeRepository bikeRepository;
	@Mock
	AdminRepository adminRepository;
	
	@Test
	void testGetAllbikes() {
		List<Bikemodel> bikes= new ArrayList<>();
		Bikemodel bike1=new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		Bikemodel bike2=new Bikemodel("abce","TN 11 AB 2222", "admin1@gmail.com","available","https://picsum.photos/200/200", "15", "Activa 5G", "scooty");
		bikes.add(bike1);
		bikes.add(bike2);
		given(bikeRepository.findAll()).willReturn(bikes);
		List<Bikemodel> result=bikeservice.getAllbikes();
	    assertThat(result).contains(bike1).hasSize(2);
	    assertThat(result.get(0).getModel()).isEqualTo(bike1.getModel());
	    assertThat(result.get(1).getModel()).isEqualTo(bike2.getModel());
	}

	@Test
	void testGetBikesByCompany() {
		List<Bikemodel> bikes= new ArrayList<>();
		Bikemodel bike1=new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		Bikemodel bike2=new Bikemodel("abce","TN 11 AB 2222", "admin@gmail.com","available","https://picsum.photos/200/200", "15", "Activa 5G", "scooty");
		bikes.add(bike1);
		bikes.add(bike2);
		given(bikeRepository.findAll()).willReturn(bikes);
		List<Bikemodel> result=bikeservice.getBikesByCompany("admin@gmail.com");
	    assertThat(result).contains(bike1).hasSize(2);
	    assertThat(result.get(0).getModel()).isEqualTo(bike1.getModel());
	    assertThat(result.get(1).getModel()).isEqualTo(bike2.getModel());
	    assertThat(result.get(0).getAdminid()).isEqualTo(result.get(1).getAdminid());
	}

	@Test
	void testGetBikeById() {
		Optional<Bikemodel> bike=Optional.ofNullable(new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike"));
		given(bikeRepository.findById("abcd")).willReturn(bike);
		Object res=bikeservice.getBikeById("abcd");
		assertThat(res).isEqualTo(bike.get());
	}
	@Test
	void testGetBikeById1() {
		Object res=bikeservice.getBikeById("1234");
		assertThat(res).isEqualTo("Bike Not Exist");
	}

	@Test
	void testBikeexist() {
		BikeData bike=new BikeData("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		given(bikeRepository.existsById("abcd")).willReturn(true);
		given(bikeRepository.existsByBikeno("TN 00 AB 1111")).willReturn(true);
		Object res=bikeservice.bikeexist(bike);
		assertThat(res).isEqualTo(true);
	}
	@Test
	void testBikeexist1() {
		BikeData bike=new BikeData("abcd","TN 00 AB 1112", "admin1@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		Object res=bikeservice.bikeexist(bike);
		assertThat(res).isEqualTo(false);
	}
	@Test
	void testBikeexistbyId() {
		given(bikeRepository.existsById("abcd")).willReturn(true);
		assertTrue(bikeservice.bikeexistbyId("abcd"));
	}
	@Test
	void testBikeexistbyId1() {
		assertFalse(bikeservice.bikeexistbyId("1234"));
	}

	@Test
	void testAddbike() {
		BikeData bike=new BikeData("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		given(adminRepository.existsByEmail(bike.getAdminid())).willReturn(true);
		String result=bikeservice.addbike(bike);
		assertThat(result).isEqualTo("Bike Added Successfully");
	}
	@Test
	void testAddbike1() {
		BikeData bike=new BikeData("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		String result=bikeservice.addbike(bike);
		assertThat(result).isEqualTo("Admin Not Exist");
	}
	@Test
	void testAddbike2() {
		BikeData bike=new BikeData("abcd","TN 00 AB 1112", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		given(bikeRepository.existsByBikeno(bike.getBikeno())).willReturn(true);
		String result=bikeservice.addbike(bike);
		assertThat(result).isEqualTo("Bike Already Exist");
	}
	
	@Test
	void testBookbike() {
		Optional<Bikemodel> bike=Optional.ofNullable(new Bikemodel("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike"));
		given(bikeRepository.findById("abcd")).willReturn(bike);
		String result=bikeservice.bookbike("abcd");
		assertThat(result).isEqualTo("Bike Booked Successfully");
	}
	@Test
	void testBookbike1() {
		String result=bikeservice.bookbike("1234");
		assertThat(result).isEqualTo("Bike Not Exist");
	}

	@Test
	void testEditbike() {
		BikeData bike=new BikeData("abcd","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		given(bikeRepository.existsById("abcd")).willReturn(true);
		String result=bikeservice.editbike(bike);
		assertThat(result).isEqualTo("Bike Updated Successfully");
	}
	@Test
	void testEditbike1() {
		BikeData bike=new BikeData("1234","TN 00 AB 1111", "admin@gmail.com","available","https://picsum.photos/200/200", "20", "R15", "bike");
		String result=bikeservice.editbike(bike);
		assertThat(result).isEqualTo("Bike Not Exist");
	}

	@Test
	void testDeletebike() {
		given(bikeRepository.existsById("abcd")).willReturn(true);
		String result=bikeservice.deletebike("abcd");
		assertThat(result).isEqualTo("Bike Deleted Successfully");
	}
	@Test
	void testDeletebike1() {
		String result=bikeservice.deletebike("1234");
		assertThat(result).isEqualTo("Bike Not Exist");
	}

}
