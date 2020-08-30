package com.gabriel.beerservice.services.order;

import com.gabriel.beerservice.config.JmsConfig;
import com.gabriel.model.events.ValidateBeerOrderRequest;
import com.gabriel.model.events.ValidateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@RequiredArgsConstructor
@Service
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;


    @Transactional
    @JmsListener(destination = JmsConfig.VALIDATE_BEER_ORDER)
    public void listen(ValidateBeerOrderRequest request) {

        Boolean isValid = validator.validateOrder(request.getBeerOrderDto());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_BEER_ORDER_RESPONSE,
                ValidateBeerOrderResult.builder()
                        .isValid(isValid)
                        .orderId(request.getBeerOrderDto().getId())
                        .build());
    }

}
