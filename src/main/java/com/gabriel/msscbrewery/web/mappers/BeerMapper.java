package com.gabriel.msscbrewery.web.mappers;


import com.gabriel.msscbrewery.domain.Beer;
import com.gabriel.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses={DateMapper.class})
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);
    Beer BeerDtoToBeer(BeerDto dto);
}
