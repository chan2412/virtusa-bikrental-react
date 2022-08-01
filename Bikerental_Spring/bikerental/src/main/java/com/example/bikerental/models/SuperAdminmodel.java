package com.example.bikerental.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class SuperAdminmodel {
    @Id
    String email;
    @NotBlank
    String password;
    @NotBlank
    String userrole;
    public SuperAdminmodel(String email, String password, String userrole) {
        this.email = email;
        this.password = password;
        this.userrole = userrole;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
