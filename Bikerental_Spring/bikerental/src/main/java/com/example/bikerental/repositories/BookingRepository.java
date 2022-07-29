package com.example.bikerental.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.bikerental.models.Bookingmodel;

public interface BookingRepository extends CrudRepository<Bookingmodel,String> {
    public List<Bookingmodel> findByAdminid(String adminid);
    public List<Bookingmodel> findByUserid(String userid);
}
