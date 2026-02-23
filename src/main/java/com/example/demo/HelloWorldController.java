package com.example.demo;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello")
    public String hello() {
        return messageSource.getMessage("hello.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorld() {    
        return new HelloWorldBean("Hello World");
    }
}
