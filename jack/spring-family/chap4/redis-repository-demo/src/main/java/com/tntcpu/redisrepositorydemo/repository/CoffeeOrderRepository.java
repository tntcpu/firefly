package com.tntcpu.redisrepositorydemo.repository;

import com.tntcpu.redisrepositorydemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
