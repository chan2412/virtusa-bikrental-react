package com.example.bikerental.models;


public record AuthenticationResponse(String jwt,String userid, String userrole) {}
