package com.gabriel.beerservice.services;

import com.gabriel.beerservice.config.JmsConfig;
import com.gabriel.beerservice.domain.Beer;
import com.gabriel.beerservice.events.BrewBeerEvent;
import com.gabriel.beerservice.repositories.BeerRepository;
import com.gabriel.beerservice.services.inventory.BeerInventoryService;
import com.gabriel.beerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

private final BeerRepository beerRepository;
private final BeerInventoryService beerInventoryService;
private final JmsTemplate jmsTemplate;
private final BeerMapper mapper;


@Scheduled(fixedRate = 5000)//every 5 seconds
public void checkForLowInventory(){

    List<Beer> beers = beerRepository.findAll();

    beers.forEach(beer->{
        Integer invQOH=beerInventoryService.getQtyOnHand(beer.getId());

        log.debug("Min on hand is "+beer.getMinOnHand());
        log.debug("Inventory is "+invQOH);

        if(invQOH <= beer.getMinOnHand()){
            jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(mapper.beerToBeerDto(beer)));
        }
    });

}

}
