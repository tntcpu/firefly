package com.tntcpu.cachedemo.repository;

import com.tntcpu.cachedemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
}
