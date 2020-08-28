package com.gabriel.model.events;

import com.gabriel.model.BeerDto;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
