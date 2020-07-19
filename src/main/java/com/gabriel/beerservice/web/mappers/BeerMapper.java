package com.gabriel.beerservice.web.mappers;


import com.gabriel.beerservice.domain.Beer;
import com.gabriel.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses={DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);
}
