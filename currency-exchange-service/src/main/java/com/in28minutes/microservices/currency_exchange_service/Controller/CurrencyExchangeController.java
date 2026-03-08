package com.in28minutes.microservices.currency_exchange_service.Controller;
import com.in28minutes.microservices.currency_exchange_service.Entity.CurrencyExchange;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private Environment environment;
    
    public CurrencyExchangeController(Environment environment) {
        this.environment = environment;
    }
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange currencyExchange(@PathVariable String from, @PathVariable String to) {
        String serverPort = environment.getProperty("local.server.port");
        return new CurrencyExchange(1L, from, to, new BigDecimal(10), serverPort);
    }
}
