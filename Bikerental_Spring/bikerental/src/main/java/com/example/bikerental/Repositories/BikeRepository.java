package com.example.bikerental.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.bikerental.Models.Bikemodel;

public interface BikeRepository extends CrudRepository<Bikemodel, String> {
	public boolean existsByBikeno(String bikno);
}
