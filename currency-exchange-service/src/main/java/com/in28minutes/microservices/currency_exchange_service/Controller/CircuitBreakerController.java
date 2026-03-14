package com.in28minutes.microservices.currency_exchange_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/currency-exchange")
public class CircuitBreakerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Retry(name = "tryRetryFeature", fallbackMethod = "fallback")
    @RateLimiter(name="default")
    // @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    @GetMapping("/try")
    public String callService() {
        logger.info("Service called from exchange service 1");
        throw new RuntimeException("error in call service");
    }

    public String fallback(Exception ex) {
        logger.info("Fallback method called", ex);
        return "error from fallback from service 1";
    }
}
