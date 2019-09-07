package com.tntcpu.waiter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class WaiterServiceMoreComplexApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaiterServiceMoreComplexApplication.class, args);
    }
}
