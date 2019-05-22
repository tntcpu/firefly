package com.tntcpu.mongorepositorydemo.repository;

import com.tntcpu.mongorepositorydemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoffeeReposity extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
