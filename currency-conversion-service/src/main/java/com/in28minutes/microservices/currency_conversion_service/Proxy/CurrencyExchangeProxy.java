package com.in28minutes.microservices.currency_conversion_service.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.in28minutes.microservices.currency_conversion_service.Entity.CurrencyExchange;

@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyExchange currencyExchange(@PathVariable String from, @PathVariable String to);
}
