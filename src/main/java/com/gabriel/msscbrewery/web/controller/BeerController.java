package com.gabriel.msscbrewery.web.controller;

import com.gabriel.msscbrewery.services.BeerService;
import com.gabriel.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addBeer(@RequestBody BeerDto beerDto) {

        BeerDto savedDto = beerService.saveBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto){
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
