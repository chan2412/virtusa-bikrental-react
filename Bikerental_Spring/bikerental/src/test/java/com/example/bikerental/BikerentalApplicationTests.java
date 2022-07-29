package com.example.bikerental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BikerentalApplicationTests {

	@Test
	void contextLoads() {
		BikerentalApplication bikerentalApplication=new BikerentalApplication();
		  assertThat(bikerentalApplication).isNotNull();
	}

}
