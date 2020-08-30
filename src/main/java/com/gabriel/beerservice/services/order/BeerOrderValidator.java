package com.gabriel.beerservice.services.order;

import com.gabriel.beerservice.repositories.BeerRepository;
import com.gabriel.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {

        return !beerOrderDto.getBeerOrderLines()
                .removeIf(orderLine -> beerRepository.findBeerByUpc(orderLine.getUpc()) == null);

    }

}
