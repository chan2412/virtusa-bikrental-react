package com.example.bikerental.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bikerental.Models.Usermodel;

@Repository
public interface UserRepository extends CrudRepository<Usermodel, String> {
public boolean existsByEmail(String email);
public boolean existsByUsername(String username);
public boolean existsByEmailAndPassword(String email, String password);
public Usermodel findByUsername(String username);
}