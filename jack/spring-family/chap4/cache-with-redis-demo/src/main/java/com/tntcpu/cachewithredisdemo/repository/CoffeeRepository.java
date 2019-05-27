package com.tntcpu.cachewithredisdemo.repository;

import com.tntcpu.cachewithredisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
