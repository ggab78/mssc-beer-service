package com.gabriel.beerservice.services;

import com.gabriel.model.BeerDto;
import com.gabriel.model.BeerPagedList;
import com.gabriel.model.BeerStyleEnum;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand);

    BeerDto getBeerByUpc(String upc);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle,boolean showInventoryOnHand, Pageable pageable);

    void deleteBeer(UUID beerId);
}
