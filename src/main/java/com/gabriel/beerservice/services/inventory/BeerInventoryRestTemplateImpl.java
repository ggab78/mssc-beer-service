package com.gabriel.beerservice.services.inventory;

import com.gabriel.beerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("!local-discovery")
@Setter
@ConfigurationProperties(prefix = "mssc.brewery", ignoreUnknownFields = false)
@Component
@RequiredArgsConstructor
public class BeerInventoryRestTemplateImpl implements BeerInventoryService {

    public static final String PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplateBuilder restTemplateBuilder;

    private String beerInventoryHost;
    private String beerInventoryPassword;
    private String beerInventoryUser;



    @Override
    public Integer getQtyOnHand(UUID beerId) {



        ResponseEntity<List<BeerInventoryDto>> responseEntity = buildRestTemplate()
                .exchange(beerInventoryHost + PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) beerId);

        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }

    private RestTemplate buildRestTemplate(){

        return restTemplateBuilder
                .basicAuthentication(beerInventoryUser, beerInventoryPassword)
                .build();
    }

}
