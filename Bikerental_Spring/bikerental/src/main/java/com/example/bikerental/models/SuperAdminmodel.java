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
    public String getUserrole() {
        return userrole;
    }
    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
    public SuperAdminmodel() {
    }
    public SuperAdminmodel(String email, String password, String userrole) {
        this.email = email;
        this.password = password;
        this.userrole = userrole;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
