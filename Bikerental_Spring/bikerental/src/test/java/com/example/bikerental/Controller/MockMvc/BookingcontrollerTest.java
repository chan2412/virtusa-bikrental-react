package com.example.bikerental.Controller.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.bikerental.Controller.Admincontroller;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookingcontrollerTest {
	Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private MockMvc mvc;
	String jwt;

	@BeforeEach
	public void exampleAuth() throws Exception {
		MvcResult result = mvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"user\",\"password\":\"user\"}"))
				.andExpect(status().isOk()).andReturn();
		jwt = JsonPath.read(result.getResponse().getContentAsString(), ("$.jwt"));
		logger.info(jwt);
	}

	@Test
	void testGetAllBookings() throws Exception {
		MvcResult result = mvc
				.perform(get("/bookings/admin@gmail.com").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
				.andExpect(status().isOk()).andReturn();
		String res = result.getResponse().getContentAsString();
		int length = JsonPath.parse(res).read("$.length()");
		assertThat(length).isEqualTo(2);
	}

	@Test
	void testGetMyBookings() throws Exception {
		MvcResult result = mvc.perform(get("/mybookings/user").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
				.andExpect(status().isOk()).andReturn();
		String res = result.getResponse().getContentAsString();
		int length = JsonPath.parse(res).read("$.length()");
		assertThat(length).isEqualTo(2);
	}

	@Test
	void testGetBooking() throws Exception {
		MvcResult result = mvc.perform(get("/booking/bidehqb73f4i/").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
				.andExpect(status().isOk()).andReturn();
		String res = result.getResponse().getContentAsString();
		logger.info(res);
		String model = JsonPath.parse(res).read("$.bikemodel");
		assertThat(model).isEqualTo("R15");
	}

	@Test
	void testAddBooking() throws Exception {
		MvcResult result= mvc.perform(post("/booking/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"bookingid\":\"1\",\"adminid\":\"admin2@gmail.com\",\"bikeid\":\"1\",\"bikemodel\":\"activa 5G\",\"companyname\":\"test\",\"days\":\"2\",\"rent\":\"40\",\"totalprice\":\"80\",\"userid\":\"user2\"}")
				.header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt)
				).andExpect(status().isOk())
		.andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Booked Successfully",res);
	}

	@Test
	void testCancelBooking() throws Exception {
		MvcResult result=mvc.perform(delete("/booking/1").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
				.andExpect(status().isOk())
				.andReturn();
String res=result.getResponse().getContentAsString();
assertThat(res).isEqualTo("Cancelled Successfully");
logger.info("Getting"+result.getResponse().getContentAsString());
	}

}
