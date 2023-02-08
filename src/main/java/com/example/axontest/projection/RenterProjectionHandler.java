package com.example.axontest.projection;

import com.example.axontest.ProjectionEventHandler;
import com.example.axontest.cqrs.renter.event.RenterCreatedEvent;
import com.example.axontest.cqrs.renter.event.RenterDeletedEvent;
import com.example.axontest.cqrs.renter.event.RenterUpdatedEvent;
import com.example.axontest.entity.Renter;
import com.example.axontest.repository.RenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class RenterProjectionHandler {

    private final RenterRepository repository;


    @ProjectionEventHandler
    public void on(RenterCreatedEvent event){
        Renter renter = new Renter();
        renter.setId(event.getId());
        renter.setFirstName(event.getFirstName());
        renter.setLastName(event.getLastName());
        renter.setAge(event.getAge());
        repository.save(renter);
    }


    @ProjectionEventHandler
    public void on(RenterUpdatedEvent event){
        Renter renter = repository
                .findById(event.getId()).orElseThrow(() -> new EntityNotFoundException("Renter given by id not found"));
        renter.setFirstName(event.getFirstName());
        renter.setAge(event.getAge());
        repository.save(renter);
    }


    @ProjectionEventHandler
    public void on(RenterDeletedEvent event){
        repository.deleteById(event.getId());
    }
}
