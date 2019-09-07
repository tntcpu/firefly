package com.tntcpu.waiter.controller;

import com.tntcpu.waiter.controller.request.NewOrderRequest;
import com.tntcpu.waiter.model.Coffee;
import com.tntcpu.waiter.model.CoffeeOrder;
import com.tntcpu.waiter.service.CoffeeOrderService;
import com.tntcpu.waiter.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffees = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffees);
    }


}
