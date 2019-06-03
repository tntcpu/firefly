package com.tntcpu.redisdemo.repository;

import com.tntcpu.redisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
