package com.company.web.springiocdemo.codeconfig.config;

import com.company.web.springiocdemo.codeconfig.repositories.BeerRepository;
import com.company.web.springiocdemo.codeconfig.repositories.BeerRepositoryImpl;
import com.company.web.springiocdemo.codeconfig.services.BeerService;
import com.company.web.springiocdemo.codeconfig.services.BeerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BeerRepository beerRepository() {
        return new BeerRepositoryImpl();
    }

    @Bean
    public BeerService beerService() {
        return new BeerServiceImpl(beerRepository());
    }

}
