package com.example.bikerental;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.bikerental.Models.Adminmodel;
import com.example.bikerental.services.Adminservice;

@WebMvcTest
class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Adminservice adminservice;

  
    @Autowired 
    private Adminmodel adminmodel;

    Adminmodel RECORD_1 = new Adminmodel("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);
    @Test
    void givenListOfAdminmodels_whenGetAllAdminmodels_thenReturnAdminmodelsList() throws Exception{
        List<Adminmodel> listOfAdmins = new ArrayList<>();
        listOfAdmins.add(RECORD_1);
        given(adminservice.getAllAdmins()).willReturn(listOfAdmins);
        ResultActions response = mockMvc.perform(get("/admins"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfAdmins.size())));

    }

 
    @Test
    void givenAdminmodelId_whenGetAdminmodelById_thenReturnAdminmodelObject() throws Exception{
     
        long employeeId = 1L;
        Adminmodel RECORD_2 = new Adminmodel("abcd","123@gmail.com", "password", "1234567890", "Cebu Philippines","cebu","admin","https://picsum.photos/200/200","123,asdf",0);

        given(adminservice.getAdmin("1234")).willReturn(RECORD_2);

        
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

       
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.email", is(adminmodel.getEmail())))
                .andExpect(jsonPath("$.mobilenumber", is(adminmodel.getMobilenumber())))
                .andExpect(jsonPath("$.companyname", is(adminmodel.getCompanyname())));

    }
}