package com.tntcpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;

@SpringBootApplication
public class CommandLineApplication {
    public static void main(String[] args) {
//        new SpringApplicationBuilder(CommandLineApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);

        SpringApplication.run(CommandLineApplication.class, args);
    }
}
