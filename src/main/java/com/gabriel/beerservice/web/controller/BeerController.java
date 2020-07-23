package com.gabriel.beerservice.web.controller;

import com.gabriel.beerservice.services.BeerService;
import com.gabriel.beerservice.web.model.BeerDto;
import com.gabriel.beerservice.web.model.BeerPagedList;
import com.gabriel.beerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final boolean DEFAULT_SHOW_INVENTORY = false;

    private final BeerService beerService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value= "showInventoryOnHand", required = false) boolean showInventoryOnHand) {

        pageNumber = Optional.ofNullable(pageNumber)
                .filter(n -> n > 0)
                .orElse(DEFAULT_PAGE_NUMBER);

        pageSize = Optional.ofNullable(pageSize)
                .filter(n -> n > 0)
                .orElse(DEFAULT_PAGE_SIZE);

        showInventoryOnHand = Optional.ofNullable(showInventoryOnHand)
                .filter(b -> b)
                .orElse(DEFAULT_SHOW_INVENTORY);

        BeerPagedList beers = beerService.listBeers(beerName, beerStyle, showInventoryOnHand, PageRequest.of(pageNumber,pageSize));

        return new ResponseEntity<>(beers, HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(value= "showInventoryOnHand", required = false) boolean showInventoryOnHand) {

        showInventoryOnHand = Optional.ofNullable(showInventoryOnHand)
                .filter(b -> b)
                .orElse(DEFAULT_SHOW_INVENTORY);
        return new ResponseEntity<>(beerService.getBeerById(beerId,showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

        BeerDto savedDto = beerService.saveBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeer(beerId);
    }


}
