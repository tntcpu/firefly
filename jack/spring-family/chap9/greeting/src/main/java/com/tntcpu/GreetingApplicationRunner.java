package com.tntcpu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {
    private String name;
    public GreetingApplicationRunner() {
        this("Zzttime");
    }
    public GreetingApplicationRunner(String name){
        this.name = name;
        log.info("Initializing GreetingApplicationRunner for {}.",name);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello everyone!We all like Spring!");
    }
}
