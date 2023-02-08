package com.example.axontest.projection;

import com.example.axontest.ProjectionEventHandler;
import com.example.axontest.cqrs.bike.event.*;
import com.example.axontest.entity.Bike;
import com.example.axontest.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class BikeProjectionHandler {
    private final BikeRepository repository;


    @ProjectionEventHandler
    public void on(BikeCreatedEvent event) {
        Bike bike = new Bike();
        bike.setId(event.getId());
        bike.setName(event.getName());
        bike.setDescription(event.getDescription());
        bike.setCreatedAt(event.getCreatedAt());
        repository.save(bike);
    }

    @ProjectionEventHandler
    public void on(BikeDeletedEvent event) {
        repository.deleteById(event.getId());
    }


    @ProjectionEventHandler
    public void on(BikeUpdatedEvent event) {
        Bike bike = repository
                .findById(event.getId()).orElseThrow(() -> new EntityNotFoundException("Bike with given id not found"));
        bike.setName(event.getName());
        bike.setDescription(event.getDescription());
        repository.save(bike);
    }


    @ProjectionEventHandler
    public void on(BikeRentedEvent event) {
        Bike bike = repository
                .findById(event.getId()).orElseThrow(() -> new EntityNotFoundException("Bike with given id not found"));
        bike.setRented(Boolean.TRUE);
        bike.setRenterId(event.getRenterId());
        repository.save(bike);
    }


    @ProjectionEventHandler
    public void on(BikeReturnedEvent event) {
        Bike bike = repository
                .findById(event.getId()).orElseThrow(() -> new EntityNotFoundException("Bike with given id not found"));
        bike.setMileAge(event.getRentalMileAge() + bike.getMileAge());
        bike.setRented(Boolean.FALSE);
        bike.setRenterId(null);
        repository.save(bike);
    }

}
