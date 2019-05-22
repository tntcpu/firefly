package com.tntcpu.mongorepositorydemo;

import com.tntcpu.mongorepositorydemo.converter.MoneyReadConverter;
import com.tntcpu.mongorepositorydemo.model.Coffee;
import com.tntcpu.mongorepositorydemo.repository.CoffeeReposity;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.LongAccumulator;

@Slf4j
@EnableMongoRepositories
@SpringBootApplication
public class MongoRepositoryDemoApplication implements CommandLineRunner {

    @Autowired
    private CoffeeReposity coffeeReposity;

    public static void main(String[] args) {
        SpringApplication.run(MongoRepositoryDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions(){
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(String... args) throws Exception {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"),20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"),30.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        coffeeReposity.insert(Arrays.asList(espresso,latte));
        coffeeReposity.findAll(Sort.by("name"))
                .forEach(c -> log.info("Saved Coffee {}",c));

        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"),35.0));
        latte.setUpdateTime(new Date());
        coffeeReposity.save(latte);
        coffeeReposity.findByName("latte")
                .forEach(c -> log.info("Coffee {}",c));

        coffeeReposity.deleteAll();

    }
}
