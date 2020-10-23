package com.gabriel.beerservice.bootstrap;

import com.gabriel.beerservice.domain.Beer;
import com.gabriel.beerservice.repositories.BeerRepository;
import com.gabriel.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    @Override
    public void run(String... args) throws Exception {
        //loadBeerObjects();
    }

    private void loadBeerObjects(){

        if(beerRepository.count()==0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bob")
                    .beerStyle(BeerStyleEnum.IPA.name())
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyleEnum.ALE.name())
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Hugo")
                    .beerStyle(BeerStyleEnum.PALE_ALE.name())
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }
}
