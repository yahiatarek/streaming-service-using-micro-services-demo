package com.in28minutes.microservices.currency_exchange_service.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
@RequestMapping("/currency-exchange")
public class CircuitBreakerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    @Bulkhead(name = "default", fallbackMethod = "fallback")
    @GetMapping("/try")
    public String callService() {
        logger.info("Service called from exchange service 2");
        throw new RuntimeException("error in call service");
    }

    public String fallback(Exception ex) {
        logger.info("Fallback method called", ex);
        return "error from fallback from service 2";
    }
}
