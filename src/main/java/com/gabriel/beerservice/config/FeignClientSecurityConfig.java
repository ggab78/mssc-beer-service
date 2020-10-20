package com.gabriel.beerservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

@Configuration
public class FeignClientSecurityConfig {

    public BasicAuthenticationInterceptor basicAuthenticationInterceptor(
            @Value("${mssc.brewery.beer-inventory-password}") String password,
            @Value("${mssc.brewery.beer-inventory-user}") String user) {

        return new BasicAuthenticationInterceptor(user, password);
    }


}
