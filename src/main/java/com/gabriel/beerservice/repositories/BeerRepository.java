package com.gabriel.beerservice.repositories;

import com.gabriel.beerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Optional<Beer> findBeerByUpc(String upc);

}
