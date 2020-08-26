package com.gabriel.beerservice.services.brewing;

import com.gabriel.beerservice.config.JmsConfig;
import com.gabriel.beerservice.domain.Beer;
import com.gabriel.beerservice.events.BrewBeerEvent;
import com.gabriel.beerservice.events.NewInventoryEvent;
import com.gabriel.beerservice.repositories.BeerRepository;
import com.gabriel.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {

        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.findById(beerDto.getId()).orElseThrow(() -> new RuntimeException("Beer not found"));

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer "+beerDto.getBeerName()+" min on hand " + beer.getMinOnHand() + " : QOH " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }

}
