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
import org.springframework.http.ResponseEntity;

import com.example.bikerental.controller.Admincontroller;
import com.example.bikerental.models.Admindata;
import com.example.bikerental.models.Adminmodel;
import com.example.bikerental.services.Adminservice;



@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AdmincontrollerTest {
	Logger logger = LogManager.getLogger(AdmincontrollerTest.class);
	@InjectMocks
	Admincontroller admincontroller;

	@Mock
	Adminservice adminservice;
	
	@Test
	void testGetAdmins() {
	    Adminmodel RECORD_1 = new Adminmodel("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
	    Adminmodel RECORD_2 = new Adminmodel("abce","1234@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);
	    Admindata admindata = new Admindata("abce","1234@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);
	    Adminmodel newAdmindata=new Adminmodel(admindata);
	    List<Adminmodel> admins= new ArrayList<>();
	    admins.add(RECORD_1);
	    admins.add(RECORD_2);
	    when(adminservice.getAllAdmins()).thenReturn(admins);
	    ResponseEntity<List<Adminmodel>> result= admincontroller.getAdmins();
	    RECORD_2.setPassword("user");
	    assertThat(result.getBody()).isEqualTo(admins);
	    assertThat(result.getBody().get(0).getEmail()).isEqualTo(RECORD_1.getEmail());
	    assertThat(result.getBody().get(1).getPassword()).isEqualTo(RECORD_2.getPassword());
	    assertThat(result.getBody().get(1).getCompanyaddress()).isEqualTo(RECORD_2.getCompanyaddress());
	    assertThat(result.getBody().get(1).getCompanyname()).isEqualTo(RECORD_2.getCompanyname());
	    assertThat(result.getBody().get(1).getCompanyimageurl()).isEqualTo(RECORD_2.getCompanyimageurl());
	    assertThat(result.getBody().get(1).getEarnings()).isEqualTo(RECORD_2.getEarnings());
	    assertThat(result.getBody().get(1).getMobilenumber()).isEqualTo(RECORD_2.getMobilenumber());
	    assertThat(result.getBody().get(1).getId()).isEqualTo(RECORD_2.getId());
	    assertThat(result.getBody().get(1).getSellername()).isEqualTo(RECORD_2.getSellername());
	    assertThat(result.getBody().get(1).getUserrole()).isEqualTo(RECORD_2.getUserrole());
	   assertThat(newAdmindata.getEmail()).isEqualTo(RECORD_2.getEmail());
	   assertThat(RECORD_2.getPassword()).isEqualTo("user");
	}

	@Test
	void testEditAdmin() {
		Admindata rec =  new Admindata("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
		when(adminservice.editAdmin(rec)).thenReturn("Admin Updated successfully");
		ResponseEntity<String> result=admincontroller.editAdmin(rec);
		assertThat(result.getBody()).isEqualTo("Admin Updated successfully");
	}

	@Test
	void testGetAdmin() {
		Adminmodel RECORD_1 = new Adminmodel("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
		when(adminservice.getAdmin("abcd")).thenReturn(RECORD_1);
		Object result= admincontroller.getAdmin("abcd");
		assertThat(result).isEqualTo(RECORD_1);
		assertThat(((Adminmodel) result).getMobilenumber()).isEqualTo(RECORD_1.getMobilenumber());
	}

}
