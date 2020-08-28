package com.gabriel.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.beerservice.services.BeerService;
import com.gabriel.model.BeerDto;
import com.gabriel.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto beerDto;

    @BeforeEach
    void setUp() {
        beerDto = BeerDto.builder()
                .beerName("test")
                .beerStyle(BeerStyleEnum.GOSE)
                .price(new BigDecimal("12.95"))
                .upc("0023423452435")
                .build();
    }

    @Test
    void getBeerById() throws Exception {

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewBeer() throws Exception {

        String beerJson = objectMapper.writeValueAsString(beerDto);
        System.out.println(beerJson);

        beerDto.setId(UUID.randomUUID());

        given(beerService.saveBeer(any(BeerDto.class))).willReturn(beerDto);

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateBeerById() throws Exception {

        String beerJson = objectMapper.writeValueAsString(beerDto);
        given(beerService.updateBeer(any(UUID.class), any(BeerDto.class))).willReturn(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isNoContent());
    }
}