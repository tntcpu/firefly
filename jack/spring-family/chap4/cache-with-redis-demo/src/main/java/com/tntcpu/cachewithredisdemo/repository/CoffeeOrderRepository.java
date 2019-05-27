package com.tntcpu.cachewithredisdemo.repository;

import com.tntcpu.cachewithredisdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
