package com.gabriel.beerservice.events;

import com.gabriel.beerservice.web.model.BeerDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


@Data
@RequiredArgsConstructor

public class BeerEvent implements Serializable {

    static final long serialVersionUID= 2619215012701084647L;

    private final BeerDto beerDto;

}