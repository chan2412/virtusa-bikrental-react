package com.example.bikerental.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

import com.example.bikerental.Models.Admindata;
import com.example.bikerental.Models.Userdata;
import com.example.bikerental.services.Adminservice;
import com.example.bikerental.services.MyUserDetailsService;
import com.example.bikerental.services.Userservices;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AuthcontrollerTest {
	Logger logger = LogManager.getLogger(AuthcontrollerTest.class);
	@InjectMocks
	Authcontroller authcontroller;

	@Mock
	Userservices userservices;
	@Mock
	MyUserDetailsService myUserDetailsService;
	@Mock
	Adminservice adminservice;
	@Test
	void testAdduser() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		logger.info(userservices);
		when(userservices.adduser(any(Userdata.class))).thenReturn("User added successfully");
	    Userdata RECORD_1 = new Userdata("user@gmail.com", "password","user", "1234567890", 25,"user");
    String result = authcontroller.adduser(RECORD_1);
    assertThat(result).isEqualTo("User added successfully");
	}

	@Test
	void testAddadmin() {
		MockHttpServletRequest request =new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(adminservice.addAdmin(any(Admindata.class))).thenReturn("Admin added successfully");
	    Admindata RECORD_1 = new Admindata("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
	    String result = authcontroller.addadmin(RECORD_1);
	    assertThat(result).isEqualTo("Admin added successfully");
	}

//	@Test
//	void testCreateAuthenticationToken() {
//		MockHttpServletRequest request =new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//		AuthenticationRequest authenticationRequest=new AuthenticationRequest("user","user");
//		when(myUserDetailsService.loadUserByUsername("user")).thenReturn(new User( "user","user",new ArrayList<>()));
//		ResponseEntity<AuthenticationResponse> response=authcontroller.createAuthenticationToken(authenticationRequest);
//		assertThat(response.getBody().userid()).isEqualTo("user");
//		assertThat(response.getBody().userrole()).isEqualTo("user");
//		assertThat(response.getBody().jwt()).isNot(null);
//	}

}
