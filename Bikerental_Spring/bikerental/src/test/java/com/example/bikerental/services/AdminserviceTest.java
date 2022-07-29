package com.example.bikerental.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bikerental.models.Admindata;
import com.example.bikerental.models.Adminmodel;
import com.example.bikerental.models.Loginmodel;
import com.example.bikerental.repositories.AdminRepository;

@ExtendWith(MockitoExtension.class)
class AdminserviceTest {
	Logger logger = LogManager.getLogger(AdminserviceTest.class);
	@InjectMocks
	Adminservice adminservice;

	@Mock
	AdminRepository adminRepository;
	@Mock
BCryptPasswordEncoder passwordEncoder;
	@Test
	void testGetAllAdmins() {
		Adminmodel RECORD_1 = new Adminmodel("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
	    Adminmodel RECORD_2 = new Adminmodel("abce","1234@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);    
	    List<Adminmodel> admins= new ArrayList<>();
	    admins.add(RECORD_1);
	    admins.add(RECORD_2);
	    given(adminRepository.findAll()).willReturn(admins);
		List<Adminmodel> resultAdminmodels=adminservice.getAllAdmins();
		assertThat(resultAdminmodels).hasSize(2);
	}

	@Test
	void testAddAdmin() {
		Admindata record= new Admindata("abce","1234@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);    
		given(adminRepository.existsByEmail(anyString())).willReturn(false);
		given(adminRepository.save(any(Adminmodel.class))).willReturn(null);
		String result=adminservice.addAdmin(record);
		assertThat(result).isEqualTo("Admin Added Successfully");
	}
	@Test
	void testAddAdmin1() {
		Admindata record= new Admindata("abce","admin@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);    
		given(adminRepository.existsByEmail("admin@gmail.com")).willReturn(true);
		String result=adminservice.addAdmin(record);
		assertThat(result).isEqualTo("Admin Already Exist");
	}

	@Test
	void testEditAdmin() {
		Admindata record= new Admindata("abce","1234@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);    
		given(adminRepository.existsByEmail(anyString())).willReturn(true);
		given(adminRepository.save(any(Adminmodel.class))).willReturn(null);
		String result=adminservice.editAdmin(record);
		assertThat(result).isEqualTo("Admin Updated Successfully");
	}

	@Test
	void testEditAdmin1() {
		Admindata record= new Admindata("abce","admin@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);    
		given(adminRepository.existsByEmail("admin@gmail.com")).willReturn(false);
		String result=adminservice.editAdmin(record);
		assertThat(result).isEqualTo("Admin Not Exist");
	}

	@Test
	void testGetAdmin() {
		Adminmodel record= new Adminmodel("abce","admin@gmail.com", "password", "1234567891", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdg",0);    
		given(adminRepository.existsByEmail("admin@gmail.com")).willReturn(true);
		given(adminRepository.findByEmail("admin@gmail.com")).willReturn(record);
		Object result=adminservice.getAdmin("admin@gmail.com");
		assertThat(result).isEqualTo(record);
	}
	@Test
	void testGetAdmin1() {
		Object result=adminservice.getAdmin("admin1@gmail.com");
		assertThat(result).isEqualTo("Admin Not Exist");
	}
	
	@Test
	void testAdminExists() {
		Loginmodel loginmodel=new Loginmodel();
		loginmodel.setEmail("admin");
		loginmodel.setPassword("admin");
		given(adminRepository.existsByEmailAndPassword(loginmodel.getEmail(), loginmodel.getPassword())).willReturn(false);
		boolean res=adminservice.adminExists(loginmodel);
		assertThat(res).isFalse();
	}

	@Test
	void testAdminExists1() {
		Loginmodel loginmodel=new Loginmodel();
		loginmodel.setEmail("admin@gmail.com");
		loginmodel.setPassword("admin");
		given(adminRepository.existsByEmailAndPassword(loginmodel.getEmail(), loginmodel.getPassword())).willReturn(true);
		boolean res=adminservice.adminExists(loginmodel);
		assertThat(res).isTrue();
	}

	
	@Test
	void testAdminExistsByEmail() {
		given(adminRepository.existsByEmail("admin@gmail.com")).willReturn(true);
		boolean res=adminservice.adminExistsByEmail("admin@gmail.com");
		assertThat(res).isTrue();
	}
	
	@Test
	void testAdminExistsByEmail1() {
		given(adminRepository.existsByEmail("admin1@gmail.com")).willReturn(false);
		boolean res=adminservice.adminExistsByEmail("admin1@gmail.com");
		assertThat(res).isFalse();
	}


}
