package com.in28minutes.microservices.currency_exchange_service.Controller;
import com.in28minutes.microservices.currency_exchange_service.Entity.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.Repository.CurrencyExchangeRepository;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private Environment environment;
    private CurrencyExchangeRepository repository;
    
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange currencyExchange(
        @PathVariable String from,
        @PathVariable String to,
        @RequestHeader(value = "headerfromcurrencyexchangegateway", required = false) String header
    ) {
        if (header != null) {
            System.out.println("gateway added this header to service 2:" + header);
        }
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find Data for " + from + " to " + to);
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
