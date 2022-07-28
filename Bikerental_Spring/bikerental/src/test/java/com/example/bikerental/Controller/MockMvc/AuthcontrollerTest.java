package com.example.bikerental.Controller.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.bikerental.Controller.Admincontroller;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthcontrollerTest {
	Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private MockMvc mvc;
	String jwt;
	@Test
	void testAdduser() throws Exception {
		MvcResult result= mvc.perform(post("/user/register/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"user1@gmail.com\",\"password\":\"user\",\"username\":\"user1\",\"mobilenumber\":\"9023456789\",\"age\":24,\"userrole\":\"user\"}")
				).andExpect(status().isOk())
		.andReturn();
		String res=result.getResponse().getContentAsString();
//		assertEquals(res, "user added successfully");
		logger.info("Getting "+res);
	}

	@Test
	void testAddadmin() throws Exception {
		MvcResult result= mvc.perform(post("/admin/register/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"idqjb7724xm\",\"email\":\"admin2@gmail.com\",\"password\":\"admin\",\"mobilenumber\":\"8123456789\",\"sellername\":\"test\",\"userrole\":\"admin1\",\"companyname\":\"test1\",\"companyimageurl\":\"https://picsum.photos/200/200\",\"companyaddress\":\"test\",\"earnings\":0}")
				).andExpect(status().isOk())
		.andReturn();
		String res=result.getResponse().getContentAsString();
//		assertEquals(res, "admin added successfully");
		logger.info("Getting "+res);
	}
	
	@Test
	void testCreateAuthenticationToken() throws Exception {
		MvcResult result1= mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"user\",\"password\":\"user\"}")
				).andExpect(status().isOk())
		.andReturn();
		String jwt1=JsonPath.read(result1.getResponse().getContentAsString(),("$.jwt"));
		MvcResult result2= mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"admin@gmail.com\",\"password\":\"admin\"}")
				).andExpect(status().isOk())
		.andReturn();
		String jwt2=JsonPath.read(result2.getResponse().getContentAsString(),("$.jwt"));
		MvcResult result3= mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"superadmin@gmail.com\",\"password\":\"admin\"}")
				).andExpect(status().isOk())
		.andReturn();
		String jwt3=JsonPath.read(result3.getResponse().getContentAsString(),("$.jwt"));
		assertNotEquals(null, jwt1);
		assertNotEquals(null, jwt2);
		assertNotEquals(null, jwt3);
	}
}
