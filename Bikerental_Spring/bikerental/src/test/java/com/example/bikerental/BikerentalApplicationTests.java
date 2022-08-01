package com.example.bikerental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BikerentalApplicationTests {
	@Autowired
	BikerentalApplication bikerentalApplication;
	@Test
	void contextLoads() {
		BikerentalApplication.main(new String[] {});
		assertThat(bikerentalApplication).isNotNull();
	}

}
