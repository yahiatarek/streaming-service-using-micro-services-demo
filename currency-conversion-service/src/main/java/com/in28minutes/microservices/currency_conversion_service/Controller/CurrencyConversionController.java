package com.in28minutes.microservices.currency_conversion_service.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import com.in28minutes.microservices.currency_conversion_service.Entity.CurrencyConversion;

@RestController
public class CurrencyConversionController {
    @GetMapping("/currency-Conversion/quantity/{quantity}/{from}/{to}")
    public CurrencyConversion retrieveConversionValue(
        @PathVariable BigDecimal quantity,
        @PathVariable String from,
        @PathVariable String to
    ) {
        RestTemplate http = new RestTemplate();

        ResponseEntity<CurrencyConversion> response = 
            http.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
            CurrencyConversion.class,
            from, to
        );

        CurrencyConversion body = response.getBody();
        if (body == null) {
            return null;
        }

        return new CurrencyConversion(
            body.getId(),
            from,
            to,
            quantity,
            body.getConversionMultiple(),
            quantity.multiply(body.getConversionMultiple()),
            body.getEnvironment()
        );
    }
}
