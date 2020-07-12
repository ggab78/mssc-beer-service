package com.gabriel.msscbrewery.services;

import com.gabriel.msscbrewery.domain.Beer;
import com.gabriel.msscbrewery.repositories.BeerRepository;
import com.gabriel.msscbrewery.web.mappers.BeerMapper;
import com.gabriel.msscbrewery.web.model.BeerDto;
import com.gabriel.msscbrewery.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.GOSE)
                .build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        Beer beer =  beerMapper.BeerDtoToBeer(beerDto);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.BeerToBeerDto(savedBeer);
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo - need implementation
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleting beer...");
    }
}
