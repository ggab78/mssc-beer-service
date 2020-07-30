package com.gabriel.beerservice.repositories;

import com.gabriel.beerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

    Optional<Beer> findBeerByUpc(String upc);

}
