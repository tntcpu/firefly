package com.tntcpu.support;

import com.tntcpu.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CoffeeIndicator implements HealthIndicator {
    private final CoffeeService coffeeService;

    public CoffeeIndicator(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @Override
    public Health health() {
        long count = coffeeService.getCoffeeCount();
        Health health;
        if (count > 0) {
            health = Health.up()
                    .withDetail("count", count)
                    .withDetail("message", "we have enough coffee.")
                    .build();
        } else {
            health = Health.down()
                    .withDetail("count", 0)
                    .withDetail("message", "we are out of coffee.")
                    .build();
        }
        return health;
    }
}
