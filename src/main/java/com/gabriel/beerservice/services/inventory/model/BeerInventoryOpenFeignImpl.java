package com.gabriel.beerservice.services.inventory.model;

import com.gabriel.beerservice.services.inventory.BeerInventoryService;
import com.gabriel.beerservice.services.inventory.InventoryServiceFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("local-discovery")
@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryOpenFeignImpl implements BeerInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getQtyOnHand(UUID beerId) {

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getQtyOnHand(beerId);

        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }

}
