package com.tntcpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutoconfigureDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoconfigureDemoApplication.class, args);
    }

    @Bean
    public GreetingApplicationRunner greetingApplicationRunner(){
        return new GreetingApplicationRunner("Spring");
    }
}
