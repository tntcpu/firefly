package com.tntcpu;

import com.tntcpu.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@Slf4j
public class WebClientDemoApplication implements ApplicationRunner {
    @Autowired
    private WebClient webClient;

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebClientDemoApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8080").build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);

        webClient.get()
                .uri("/coffee/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(Coffee.class)
                .doOnError(e -> log.error("Error: ", e))
                .doFinally(f -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(s -> log.info("Coffee 1: {}", s));
        Mono<Coffee> americano = Mono.just(
                Coffee.builder()
                        .name("Americano")
                        .price(Money.of(CurrencyUnit.of("CNY"), 25.00))
                        .build()
        );
        webClient.post()
                .uri("/coffee/")
                .body(americano, Coffee.class)
                .retrieve()
                .bodyToMono(Coffee.class)
                .doFinally(f -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(s -> log.info("Coffee Created: {}", s));
        cdl.await();
        webClient.get()
                .uri("/coffee/")
                .retrieve()
                .bodyToFlux(Coffee.class)
                .toStream()
                .forEach(c -> log.info("Coffee in list: {}", c));
    }
}
