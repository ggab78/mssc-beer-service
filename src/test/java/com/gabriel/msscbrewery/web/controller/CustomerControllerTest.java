package com.gabriel.msscbrewery.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.msscbrewery.services.CustomerService;
import com.gabriel.msscbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validCustomer;

    @Before
    public void setUp() throws Exception {

        validCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Gab")
                .build();

    }

    @Test
    public void getCustomer() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);
        mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is(validCustomer.getName())));


    }

    @Test
    public void createCustomer() throws Exception {

        given(customerService.createCustomer(any(CustomerDto.class))).willReturn(validCustomer);

        String body = objectMapper.writeValueAsString(validCustomer);

        mockMvc.perform(post("/api/v1/customer/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","/api/v1/customer/"+validCustomer.getId().toString()));
    }

    @Test
    public void updateCustomer() throws Exception {

        doNothing().when(customerService).updateCustomer(any(UUID.class),any(CustomerDto.class));

        String body = objectMapper.writeValueAsString(validCustomer);

        mockMvc.perform(put("/api/v1/customer/"+validCustomer.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isAccepted());
    }


    @Test
    public void deleteCustomer() throws Exception {

        doNothing().when(customerService).deleteCustomerById(any(UUID.class));

        mockMvc.perform(delete("/api/v1/customer/"+validCustomer.getId().toString()))
                .andExpect(status().isAccepted());
    }


}