package com.tntcpu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tntcpu.integration.CoffeeOrderService;
import com.tntcpu.integration.CoffeeService;
import com.tntcpu.model.Coffee;
import com.tntcpu.model.CoffeeOrder;
import com.tntcpu.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @GetMapping("/menu")
    public List<Coffee> readMenu() {
        List<Coffee> list = coffeeService.getAll();
        log.info("read menu: {} coffee", list.size());
        return list;
    }

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackCreateOrder")
    public CoffeeOrder createOrder() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("li lei")
                .items(Arrays.asList("capuccino"))
                .build();
        CoffeeOrder order = coffeeOrderService.create(orderRequest);
        log.info("order id: {}", order != null ? order.getId() : "-");
        return order;
    }

    public CoffeeOrder fallbackCreateOrder() {
        log.warn("fallback to null order.");
        return null;
    }
}
