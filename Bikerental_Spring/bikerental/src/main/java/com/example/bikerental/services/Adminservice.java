package com.example.bikerental.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bikerental.Models.Admindata;
import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.Models.Loginmodel;
import com.example.bikerental.Repositories.AdminRepository;


@Service
public class Adminservice {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
@Autowired 
private AdminRepository adminRepository;
public List<Adminmodel> getAllAdmins()
{List<Adminmodel> admins=new ArrayList<>();
  adminRepository.findAll()
  .forEach(admins::add);
  return admins;
}
public String addAdmin(Admindata data)
{Adminmodel newadmin=new Adminmodel(data);
	String email=newadmin.getEmail();
String password=newadmin.getPassword();
String encodedPassword=passwordEncoder.encode(password);
newadmin.setPassword(encodedPassword);
 if(!adminRepository.existsByEmail(email) )
 { adminRepository.save(newadmin);
	 return "admin added successfully";
 }
 return "admin already exist";
}

public String editAdmin(Admindata updateddata)
{ Adminmodel updatedadmin=new Adminmodel(updateddata);
 if(adminRepository.existsByEmail(updatedadmin.getEmail()))
 { adminRepository.save(updatedadmin);
	 return "admin updated successfully";
 }
 return "admin not exist";
}
public Adminmodel getAdmin(String id)
{
return adminRepository.findByEmail(id);
}
public boolean adminExists(Loginmodel login)
{
	return adminRepository.existsByEmailAndPassword(login.getEmail(), login.getPassword());
}
public boolean adminExistsByEmail(String email)
{
	return adminRepository.existsByEmail(email);
}
}
