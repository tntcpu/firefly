package com.tntcpu.repository;

import com.tntcpu.model.Coffee;
import com.tntcpu.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {

}
