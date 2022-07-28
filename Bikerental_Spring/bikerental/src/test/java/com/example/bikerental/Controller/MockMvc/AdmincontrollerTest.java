package com.example.bikerental.Controller.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
class AdmincontrollerTest {
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
	void testGetAdmins() throws Exception {
		MvcResult result=
		mvc.perform(get("/admins").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
				.andExpect(status().isOk())
				.andReturn();
		String res=result.getResponse().getContentAsString();
		int length=JsonPath.parse(res).read("$.length()");
		assertThat(length).isEqualTo(2);
	}
	@Order(2)
	@Test
	void testGetAdmin() throws Exception {
		MvcResult result=mvc.perform(get("/admin/admin@gmail.com").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt))
						.andExpect(status().isOk())
						.andReturn();
		String res=result.getResponse().getContentAsString();
		String role=JsonPath.parse(res).read("$.userrole");
		assertThat(role).isEqualTo("admin");
		logger.info("Getting"+result.getResponse().getContentAsString());
	}
	@Order(3)
	@Test
	void testEditAdmin() throws Exception {
		MvcResult result= mvc.perform(put("/admin/edit/").header(HttpHeaders.AUTHORIZATION, "Bearer "+jwt)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"idqjb7724xm\",\"email\":\"admin2@gmail.com\",\"password\":\"admin\",\"mobilenumber\":\"8123456789\",\"sellername\":\"test\",\"userrole\":\"admin1\",\"companyname\":\"test1\",\"companyimageurl\":\"https://picsum.photos/200/200\",\"companyaddress\":\"test\",\"earnings\":0}")
				).andExpect(status().isOk())
		.andReturn();
		String res=result.getResponse().getContentAsString();
		assertEquals("admin updated successfully", res);
		logger.info("Getting "+res);
	}

}
