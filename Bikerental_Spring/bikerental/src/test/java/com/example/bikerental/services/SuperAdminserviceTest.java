package com.example.bikerental.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bikerental.models.Bookingmodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.BookingRepository;
import com.example.bikerental.repositories.SuperAdminRepository;

@ExtendWith(MockitoExtension.class)
class SuperAdminserviceTest {
	Logger logger = LogManager.getLogger(BikeserviceTest.class);
	@InjectMocks
	SuperAdminservice superAdminservice;
	
	@Mock
	SuperAdminRepository superAdminRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	BookingRepository bookingRepository;
	
	@Test
	void testGetAllBookings() {
		List<Bookingmodel> bookings= new ArrayList<>();
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		Bookingmodel booking2=new Bookingmodel("2", "user1", "activa 5g", "admin@gmail.com", "company","2", "15", "2", "300");
		bookings.add(booking1);
		bookings.add(booking2);
		given(bookingRepository.findAll()).willReturn(bookings);
		List<Bookingmodel> result=superAdminservice.getAllBookings();
	    assertThat(result).contains(booking1).hasSize(2);
	    assertThat(result.get(0).getBikemodel()).isEqualTo(booking1.getBikemodel());
	    assertThat(result.get(1).getBikemodel()).isEqualTo(booking2.getBikemodel());
	}

	@Test
	void testDeleteAdmin() {
		given(adminRepository.existsByEmail("admin@gmail.com")).willReturn(true);
		String result=superAdminservice.deleteAdmin("admin@gmail.com");
		assertThat(result).isEqualTo("Admin Deleted Successfully");
	}
	@Test
	void testDeleteAdmin1() {
		String result=superAdminservice.deleteAdmin("123@gmail.com");
		assertThat(result).isEqualTo("Admin Not Exist");
	}
}
