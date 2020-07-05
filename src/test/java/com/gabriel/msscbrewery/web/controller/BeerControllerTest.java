package com.gabriel.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.msscbrewery.services.BeerService;
import com.gabriel.msscbrewery.web.model.BeerDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @Before
    public void setUp() throws Exception {

        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("test name")
                .beerStyle("test style")
                .build();

    }

    @Test
    public void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(validBeer.getBeerName())));

    }

    @Test
    public void addBeer() throws Exception {
        given(beerService.saveBeer(any(BeerDto.class))).willReturn(validBeer);

        String validJson = objectMapper.writeValueAsString(validBeer);

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/beer/" + validBeer.getId().toString()));
    }

    @Test
    public void updateBeer() throws Exception {

        doNothing().when(beerService).updateBeer(any(),any());

        String validJson = objectMapper.writeValueAsString(validBeer);

        mockMvc.perform(put("/api/v1/beer/" + validBeer.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(validJson))
                .andExpect(status().isAccepted());
    }
}