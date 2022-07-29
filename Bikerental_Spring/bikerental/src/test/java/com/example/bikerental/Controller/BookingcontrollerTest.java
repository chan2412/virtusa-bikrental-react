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

import com.example.bikerental.controller.Bookingcontroller;
import com.example.bikerental.models.BookingData;
import com.example.bikerental.models.Bookingmodel;
import com.example.bikerental.services.Bookingservices;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class BookingcontrollerTest {
	Logger logger = LogManager.getLogger(AuthcontrollerTest.class);
	@InjectMocks
	Bookingcontroller bookingcontroller;
	@Mock
	Bookingservices bookingservices;
	@Test
	void testGetAllBookings() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Bookingmodel> bookings= new ArrayList<>();
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		Bookingmodel booking2=new Bookingmodel("2", "user1", "activa 5g", "admin@gmail.com", "company","2", "15", "2", "300");
		bookings.add(booking1);
		bookings.add(booking2);
		when(bookingservices.getAllBookings("admin@gmail.com")).thenReturn(bookings);
		List<Bookingmodel> result=bookingcontroller.getAllBookings("admin@gmail.com");
	    assertThat(result).contains(booking1).hasSize(2);
	    assertThat(result.get(0).getBikemodel()).isEqualTo(booking1.getBikemodel());
	    assertThat(result.get(1).getBikemodel()).isEqualTo(booking2.getBikemodel());
	}

	@Test
	void testGetMyBookings() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Bookingmodel> bookings= new ArrayList<>();
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		Bookingmodel booking2=new Bookingmodel("2", "user", "activa 5g", "admin1@gmail.com", "company","2", "15", "2", "300");
		bookings.add(booking1);
		bookings.add(booking2);
		when(bookingservices.getMyBookings("user")).thenReturn(bookings);
		List<Bookingmodel> result=bookingcontroller.getMyBookings("user");
	    assertThat(result).contains(booking1).hasSize(2);
	    assertThat(result.get(0).getBikemodel()).isEqualTo(booking1.getBikemodel());
	    assertThat(result.get(1).getBikemodel()).isEqualTo(booking2.getBikemodel());
	}

	@Test
	void testGetBooking() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Bookingmodel booking1=new Bookingmodel("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		when(bookingservices.getBooking("1")).thenReturn(booking1);
		Object result=bookingcontroller.getBooking("1");
	    assertThat(result).isEqualTo(booking1);
	}

	@Test
	void testAddBooking() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		BookingData booking1=new BookingData("1", "user", "r15", "admin@gmail.com", "company","1", "20", "2", "400");
		when(bookingservices.addBooking(booking1)).thenReturn("Booking Added Successfully");
		String result=bookingcontroller.addBooking(booking1);
	    assertThat(result).isEqualTo("Booking Added Successfully");
	}

	@Test
	void testCancelBooking() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(bookingservices.cancelBooking("1")).thenReturn("Booking Cancelled Successfully");
		String result=bookingcontroller.cancelBooking("1");
	    assertThat(result).isEqualTo("Booking Cancelled Successfully");
	}

}
