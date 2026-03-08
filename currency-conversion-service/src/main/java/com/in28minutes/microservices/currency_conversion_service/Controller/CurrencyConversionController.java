package com.in28minutes.microservices.currency_conversion_service.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

import com.in28minutes.microservices.currency_conversion_service.Entity.CurrencyConversion;
import com.in28minutes.microservices.currency_conversion_service.Entity.CurrencyExchange;
import com.in28minutes.microservices.currency_conversion_service.Proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
    private final CurrencyExchangeProxy currencyExchangeProxy;
    
    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }
    
    @GetMapping("/currency-Conversion/quantity/{quantity}/{from}/{to}")
    public CurrencyConversion retrieveConversionValue(
        @PathVariable BigDecimal quantity,
        @PathVariable String from,
        @PathVariable String to
    ) {
        CurrencyExchange currencyExchange = currencyExchangeProxy.currencyExchange(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find Data for " + from + " to " + to);
        }

        return new CurrencyConversion(
            currencyExchange.getId(),
            from,
            to,
            quantity,
            currencyExchange.getConversionMultiple(),
            quantity.multiply(currencyExchange.getConversionMultiple()),
            currencyExchange.getEnvironment() + " now from feign"
        );
    }
}
