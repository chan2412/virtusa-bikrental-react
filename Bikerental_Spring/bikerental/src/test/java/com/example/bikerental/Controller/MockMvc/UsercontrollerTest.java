package com.example.bikerental.Controller.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
class UsercontrollerTest {
	Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private MockMvc mvc;
	String jwt;
	@BeforeEach
	@Test
	void exampleAuth() throws Exception {
		MvcResult result= mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"user\",\"password\":\"user\"}")
				).andExpect(status().isOk())
		.andReturn();
		jwt=JsonPath.read(result.getResponse().getContentAsString(),("$.jwt"));
		logger.info(jwt);
	}
	@Test
	void testUpdateuser() throws Exception {
		MvcResult result= mvc.perform(put("/user/edit/").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"user@gmail.com\",\"password\":\"user\",\"username\":\"user\",\"mobilenumber\":\"9123456789\",\"age\":23,\"userrole\":\"user\"}")
				).andExpect(status().isOk())
				.andReturn();
		String res=result.getResponse().getContentAsString();
		assertEquals("User Updated successfully", res);
		logger.info("Getting "+res);
	}

	@Test
	void testGetUser() throws Exception {
		MvcResult result=mvc.perform(get("/user/user").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
				.andExpect(status().isOk())
				.andReturn();
String res=result.getResponse().getContentAsString();
String role=JsonPath.parse(res).read("$.userrole");
assertThat(role).isEqualTo("user");
logger.info("Getting"+result.getResponse().getContentAsString());
	}

}
