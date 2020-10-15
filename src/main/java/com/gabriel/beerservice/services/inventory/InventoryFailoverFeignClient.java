package com.gabriel.beerservice.services.inventory;


import com.gabriel.beerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "inventory-failover")
public interface InventoryFailoverFeignClient {


    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getQtyOnHand();

}
