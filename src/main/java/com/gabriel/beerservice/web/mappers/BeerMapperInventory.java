package com.gabriel.beerservice.web.mappers;

import com.gabriel.beerservice.domain.Beer;
import com.gabriel.beerservice.services.inventory.BeerInventoryService;

import com.gabriel.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerMapperInventory implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer){
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = beerMapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getQtyOnHand(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto dto) {
        return beerMapper.beerDtoToBeer(dto);
    }
}
