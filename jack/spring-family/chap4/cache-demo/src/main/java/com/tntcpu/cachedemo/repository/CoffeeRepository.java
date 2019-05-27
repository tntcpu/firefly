package com.tntcpu.cachedemo.repository;

import com.tntcpu.cachedemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
