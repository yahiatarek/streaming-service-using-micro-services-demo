package com.in28minutes.microservices.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/get")
                        .filters(f -> f.addRequestHeader("hello", "world")
                            .addRequestParameter("param", "value")
                        )
                        .uri("http://httpbin.org/get"))
                .route(r -> r.path("/currency-exchange/**")
                .filters(f -> f.addRequestHeader("headerfromcurrencyexchangegateway", "hello from gateway")
                            .addRequestParameter("param", "value")
                        )
                        .uri("lb://currency-exchange-service"))
                .route(r -> r.path("/currency-conversion/**")
                .filters(f -> f.addRequestHeader("headerfromcurrencyconversiongateway", "hello from gateway")
                            .addRequestParameter("param", "value")
                        )
                        .uri("lb://currency-conversion-service"))
                .route(r -> r.path("/currency-exchange-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://currency-exchange-service"))
                .route(r -> r.path("/currency-conversion-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
