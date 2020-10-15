package com.gabriel.beerservice.services.inventory;

import com.gabriel.beerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = InventoryServiceFeignClientImpl.class)
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = BeerInventoryRestTemplateImpl.PATH)
    ResponseEntity<List<BeerInventoryDto>> getQtyOnHand(@PathVariable("beerId") UUID beerId);

}
