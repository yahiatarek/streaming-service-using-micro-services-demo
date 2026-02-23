package com.example.demo;

/*
 * Alternative to @JsonView (example only, intentionally disabled):
 *
 * This ResponseBodyAdvice approach applies Jackson filter hints only for GET /users.
 * It is useful when you want dynamic filtering per endpoint without DTO classes.
 *
 * To enable it:
 * 1) Uncomment this file content.
 * 2) Add @JsonFilter("UserIdFilter") on User.
 * 3) Remove/adjust @JsonView usage to avoid mixing two filtering strategies.
 */

/*
import java.util.Collections;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@ControllerAdvice(assignableTypes = UserResource.class)
public class UserIdFilterAdviceExample implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Apply only on UserResource#getAllUsers.
        if (!(returnType.getExecutable() instanceof java.lang.reflect.Method method)) {
            return false;
        }
        return method.getName().equals("getAllUsers")
                && method.getDeclaringClass().equals(UserResource.class);
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            org.springframework.http.server.ServerHttpRequest request,
            org.springframework.http.server.ServerHttpResponse response) {

        // Body is unchanged. Filtering is applied via write hints below.
        return body;
    }

    @Override
    public Map<String, Object> determineWriteHints(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType) {

        // Spring 7+ Jackson converter reads this hint key:
        // tools.jackson.databind.ser.FilterProvider.class.getName()
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("UserIdFilter", SimpleBeanPropertyFilter.serializeAllExcept("id"));

        return Collections.singletonMap(
                tools.jackson.databind.ser.FilterProvider.class.getName(),
                filters);
    }
}
*/
