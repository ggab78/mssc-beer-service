package com.gabriel.beerservice.web.mappers;


import com.gabriel.beerservice.domain.Beer;
import com.gabriel.beerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses={DateMapper.class})
@DecoratedWith(BeerMapperInventory.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);
    BeerDto beerToBeerDtoWithInventory(Beer beer);
}
