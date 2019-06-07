package com.tntcpu.redisrepositorydemo.repository;

import com.tntcpu.redisrepositorydemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
