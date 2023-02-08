package com.example.axontest.repository;

import com.example.axontest.entity.Bike;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BikeRepository extends CrudRepository<Bike, UUID> {
    Optional<Bike> findBikeByRenterId(UUID id);
}
