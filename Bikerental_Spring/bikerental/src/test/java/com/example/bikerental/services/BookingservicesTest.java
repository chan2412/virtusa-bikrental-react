package com.example.bikerental.services;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.example.bikerental.models.BookingData;
import com.example.bikerental.models.Bookingmodel;
import com.example.bikerental.repositories.AdminRepository;
import com.example.bikerental.repositories.BookingRepository;

@ExtendWith(MockitoExtension.class)
class BookingservicesTest {
	Logger logger = LogManager.getLogger(BookingservicesTest.class);
	@InjectMocks
	Bookingservices bookingservices;
	@Mock
	BookingRepository bookingRepository;
	@Mock
	AdminRepository adminRepository;
	@Test
	void testGetBooking() {
		Optional<Bookingmodel> booking=Optional.of(new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400"));
		given(bookingRepository.findById("1")).willReturn(booking);
		Object result=bookingservices.getBooking("1");
	    assertThat(result).isEqualTo(booking.get());
	}
	@Test
	void testGetBooking1() {
		Object result=bookingservices.getBooking("2");
	    assertThat(result).isEqualTo("Booking Not Exist");
	}

	@Test
	void testGetAllBookings() {
		List<Bookingmodel> bookings= new ArrayList<>();
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		Bookingmodel booking2=new Bookingmodel("2", "user1", "activa 5g", "admin@gmail.com", "company","2", "15", "2", "300");
		bookings.add(booking1);
		bookings.add(booking2);
		given(bookingRepository.findByAdminid("admin@gmail.com")).willReturn(bookings);
		List<Bookingmodel> result=bookingservices.getAllBookings("admin@gmail.com");
	    assertThat(result).contains(booking1).hasSize(2);
	    assertThat(result.get(0).getBikemodel()).isEqualTo(booking1.getBikemodel());
	    assertThat(result.get(1).getBikemodel()).isEqualTo(booking2.getBikemodel());
	}

	@Test
	void testGetMyBookings() {
		List<Bookingmodel> bookings= new ArrayList<>();
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		Bookingmodel booking2=new Bookingmodel("2", "user", "activa 5g", "admin1@gmail.com", "company","2", "15", "2", "300");
		bookings.add(booking1);
		bookings.add(booking2);
		given(bookingRepository.findByUserid("user")).willReturn(bookings);
		List<Bookingmodel> result=bookingservices.getMyBookings("user");
	    assertThat(result).contains(booking1).hasSize(2);
	    assertThat(result.get(0).getBikemodel()).isEqualTo(booking1.getBikemodel());
	    assertThat(result.get(1).getBikemodel()).isEqualTo(booking2.getBikemodel());
	}

	@Test
	void testAddBooking() {
		BookingData booking=new BookingData("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		String result= bookingservices.addBooking(booking);
		assertThat(result).isEqualTo("Bike Booked Successfully");
	}

	@Test
	void testCancelBooking() {
		String result=bookingservices.cancelBooking("1");
		assertThat(result).isEqualTo("Booking Cancelled Successfully");
	}

}
