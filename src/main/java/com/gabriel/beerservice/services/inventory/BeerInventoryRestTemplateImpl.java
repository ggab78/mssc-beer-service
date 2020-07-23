package com.gabriel.beerservice.services.inventory;

import com.gabriel.beerservice.services.inventory.model.BeerInventoryDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@ConfigurationProperties(prefix = "mssc.brewery", ignoreUnknownFields = false)
@Component
public class BeerInventoryRestTemplateImpl implements BeerInventoryService {

    private final String PATH = "/api/v1/beer/{beerId}/inventory";

    private String beerInventoryHost;

    private final RestTemplate restTemplate;

    public void setBeerInventoryHost(String beerInventoryHost){
        this.beerInventoryHost = beerInventoryHost;
    }

    public BeerInventoryRestTemplateImpl(RestTemplateBuilder templateBuilder) {
        this.restTemplate = templateBuilder.build();
    }

    @Override
    public Integer getQtyOnHand(UUID beerId) {

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
                .exchange(beerInventoryHost + PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) beerId);

        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
