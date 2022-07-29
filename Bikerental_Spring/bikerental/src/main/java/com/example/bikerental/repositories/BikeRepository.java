package com.example.bikerental.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.bikerental.models.Bikemodel;

public interface BikeRepository extends CrudRepository<Bikemodel, String> {
	public boolean existsByBikeno(String bikno);
}
