package com.gabriel.msscbrewery.bootstrap;

import com.gabriel.msscbrewery.domain.Beer;
import com.gabriel.msscbrewery.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects(){
        if(beerRepository.count()==0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bob")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(234322000L)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(2343454700L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }

    }
}
