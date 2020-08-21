package com.gabriel.beerservice.events;

import com.gabriel.beerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class NewInventoryIvent extends BeerEvent {
    public NewInventoryIvent(BeerDto beerDto) {
        super(beerDto);
    }
}
