package com.example.bikerental.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.bikerental.models.Adminmodel;

public interface AdminRepository extends CrudRepository<Adminmodel,String>{
	public boolean existsByEmail(String email);
	public boolean existsByEmailAndPassword(String email, String password);
	public Adminmodel findByEmail(String email);
}
