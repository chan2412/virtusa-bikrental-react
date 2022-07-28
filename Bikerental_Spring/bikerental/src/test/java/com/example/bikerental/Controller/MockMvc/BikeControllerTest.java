package com.example.bikerental.Controller.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
class BikeControllerTest {
	Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private MockMvc mvc;
	String jwt;
	@BeforeEach
	public void exampleAuth() throws Exception {
		MvcResult result= mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"user\",\"password\":\"user\"}")
				).andExpect(status().isOk())
		.andReturn();
		jwt=JsonPath.read(result.getResponse().getContentAsString(),("$.jwt"));
		logger.info(jwt);
	}
	@Order(1)
	@Test
	void testGetbikes() throws Exception {
		MvcResult result=
				mvc.perform(get("/bikes").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
						.andExpect(status().isOk())
						.andReturn();
				String res=result.getResponse().getContentAsString();
				int length=JsonPath.parse(res).read("$.length()");
				assertThat(length).isEqualTo(2);
	}
	@Order(2)
	@Test
	void testGetbike() throws Exception {
		MvcResult result=
				mvc.perform(get("/bike/id1657462332727/").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
						.andExpect(status().isOk())
						.andReturn();
				String res=result.getResponse().getContentAsString();
				String model=JsonPath.parse(res).read("$.model");
				assertThat(model).isEqualTo("XL50");
	}
	@Order(3)
	@Test
	void testSavebike() throws Exception {
		MvcResult result= mvc.perform(post("/bike/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"bikeid\":1,\"bikeno\":\"2G4GT5GC2B9270094\",\"adminid\":\"admin@gmail.com\",\"status\":\"available\",\"price\":\"19\",\"model\":\"XL100\",\"type\":\"Bike\"}")
				.header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt)
				).andExpect(status().isOk())
		.andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("bike added successfully",res);
	}
	@Order(4)
	@Test
	void testBookbike() throws Exception {
		MvcResult result=
				mvc.perform(get("/bike/book/id1657462332727/").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
						.andExpect(status().isOk())
						.andReturn();
				String res=result.getResponse().getContentAsString();
				assertEquals("bike booked successfully",res);
	}
	@Order(5)
	@Test
	void testEditbike() throws Exception {
		MvcResult result= mvc.perform(put("/bike/edit/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"bikeid\":\"id1657462332727\",\"bikeno\":\"TN 00 aa 1234\",\"adminid\":\"admin@gmail.com\",\"status\":\"not available\",\"price\":\"20\",\"model\":\"XL50\",\"type\":\"Bike\"}")
				.header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt)
				).andExpect(status().isOk())
		.andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("bike updated successfully",res);
	}
	@Order(6)
	@Test
	void testDeletebike() throws Exception {
		MvcResult result=mvc.perform(delete("/bike/delete/1").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
				.andExpect(status().isOk())
				.andReturn();
String res=result.getResponse().getContentAsString();
assertThat(res).isEqualTo("bike deleted successfully");
logger.info("Getting"+result.getResponse().getContentAsString());
	}

}
