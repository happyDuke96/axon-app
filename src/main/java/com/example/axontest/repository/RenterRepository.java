package com.example.axontest.repository;

import com.example.axontest.entity.Renter;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RenterRepository extends CrudRepository<Renter, UUID> {
}
