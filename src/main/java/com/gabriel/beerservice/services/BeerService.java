package com.gabriel.beerservice.services;

import com.gabriel.beerservice.web.model.BeerDto;
import com.gabriel.beerservice.web.model.BeerPagedList;
import com.gabriel.beerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    void deleteBeer(UUID beerId);
}
