package com.tntcpu;

import com.tntcpu.model.Coffee;
import com.tntcpu.model.CoffeeOrder;
import com.tntcpu.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CustomerRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${waiter.service.url}")
    private String url;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = restTemplate
                .getForObject(url + "/order/{id}", CoffeeOrder.class, id);
        log.info("order: {}", order);
    }

    private Long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("li lei")
                .items(Arrays.asList("capuccino"))
                .build();
        RequestEntity<NewOrderRequest> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString(url + "/order/").build().toUri())
                .body(orderRequest);
        ResponseEntity<CoffeeOrder> response = restTemplate.exchange(request, CoffeeOrder.class);
        log.info("order request status code: {}", response.getStatusCode());
        Long id = response.getBody().getId();
        log.info("order id: {}", id);
        return id;
    }

    private void readMenu() {
        ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Coffee>> list = restTemplate.exchange(url + "/coffee/", HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> log.info("coffee: {}", c));
    }
}
















