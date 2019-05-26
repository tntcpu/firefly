package com.tntcpu.jedisdemo.repository;

import com.tntcpu.jedisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
