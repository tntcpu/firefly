package com.tntcpu;

import com.tntcpu.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class CustomerServiceSimpleApplication implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CustomerServiceSimpleApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);
        ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
        log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
        log.info("Coffee: {}", c.getBody());

        String coffeeUri = "http://localhost:8080/coffee/";
        Coffee request = Coffee.builder()
                .name("Americano")
                .price(BigDecimal.valueOf(25.00))
                .build();
        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
        log.info("New Coffee: {}", response);

        String s = restTemplate.getForObject(coffeeUri, String.class);
        log.info("String: {}", s);
    }
}
