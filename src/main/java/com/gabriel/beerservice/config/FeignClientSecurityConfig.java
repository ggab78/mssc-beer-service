package com.gabriel.beerservice.config;


import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientSecurityConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${mssc.brewery.beer-inventory-password}") String password,
            @Value("${mssc.brewery.beer-inventory-user}") String user) {

        return new BasicAuthRequestInterceptor(user, password);
    }


}
