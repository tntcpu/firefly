package com.tntcpu.integration;

import com.tntcpu.model.CoffeeOrder;
import com.tntcpu.model.NewOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@FeignClient(name = "consul-waiter-service", contextId = "coffeeOrder")
public interface CoffeeOrderService {
    @GetMapping("/order/{id}")
    CoffeeOrder getOrder(@PathVariable("id") Long id);

    @PostMapping(path = "/order/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    CoffeeOrder create(@RequestBody NewOrderRequest newOrder);
}
