package com.gabriel.msscbrewery.services;

import com.gabriel.msscbrewery.domain.Beer;
import com.gabriel.msscbrewery.repositories.BeerRepository;
import com.gabriel.msscbrewery.web.controller.NotFoundException;
import com.gabriel.msscbrewery.web.mappers.BeerMapper;
import com.gabriel.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerRepository.findById(beerId)
                .map(beer-> beerMapper.beerToBeerDto(beer))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        Beer beer = beerMapper.beerDtoToBeer(beerDto);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer foundBeer = beerRepository.findById(beerId)
                .map(beer -> {
                    Optional.ofNullable(beerDto.getBeerName()).ifPresent(s -> beer.setBeerName(s));
                    Optional.ofNullable(beerDto.getBeerStyle()).ifPresent(e -> beer.setBeerStyle(e.name()));
                    Optional.ofNullable(beerDto.getUpc()).ifPresent(s -> beer.setUpc(s));
                    Optional.of(beerDto.getPrice()).ifPresent(n->beer.setPrice(n));
                    return beer;
                })
                .orElseThrow(NotFoundException::new);
        return beerMapper.beerToBeerDto(beerRepository.save(foundBeer));
    }

    @Override
    public void deleteBeer(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
