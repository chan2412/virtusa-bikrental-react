package com.example.bikerental.Controller.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
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
class SuperAdmincontrollerTest {
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
	@Test
	void testGetAllAdmins() throws Exception {
		MvcResult result=
				mvc.perform(get("/superadmin/admins").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
						.andExpect(status().isOk())
						.andReturn();
				String res=result.getResponse().getContentAsString();
				int length=JsonPath.parse(res).read("$.length()");
				assertThat(length).isEqualTo(1);
	}

	@Test
	void testGetAllBookings() throws Exception {
		MvcResult result=
				mvc.perform(get("/superadmin/bookings").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
						.andExpect(status().isOk())
						.andReturn();
				String res=result.getResponse().getContentAsString();
				int length=JsonPath.parse(res).read("$.length()");
				assertThat(length).isEqualTo(2);
	}

	@Test
	void testDeleteAdmin() throws Exception {
		MvcResult result=mvc.perform(delete("/superadmin/admin/idqjb7724xm").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
				.andExpect(status().isOk())
				.andReturn();
String res=result.getResponse().getContentAsString();
assertThat(res).isEqualTo("Admin deleted Successfully");
logger.info("Getting"+result.getResponse().getContentAsString());
	}

}
