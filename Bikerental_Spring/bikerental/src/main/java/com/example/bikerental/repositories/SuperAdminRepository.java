package com.example.bikerental.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bikerental.models.SuperAdminmodel;

@Repository
public interface SuperAdminRepository extends CrudRepository<SuperAdminmodel,String> {
    public boolean existsByEmailAndPassword(String email, String password);
    public SuperAdminmodel findByEmail(String email);
}
