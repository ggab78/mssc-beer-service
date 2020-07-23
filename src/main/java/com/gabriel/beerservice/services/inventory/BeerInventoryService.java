package com.gabriel.beerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getQtyOnHand(UUID beerId);
}
