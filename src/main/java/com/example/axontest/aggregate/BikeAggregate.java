package com.example.axontest.aggregate;

import com.example.axontest.cqrs.bike.command.*;
import com.example.axontest.cqrs.bike.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.UUID;

@Aggregate
public class BikeAggregate {

    private UUID id;
    private UUID renterId;
    private String name;
    private String description;
    private Instant createdAt;
    private Boolean rented;
    private Double mileAge;

    public BikeAggregate() {
        // no-arg constructor, which is required by Axon.
    }

    @CommandHandler
    public BikeAggregate(BikeCreateCommand command) {
        AggregateLifecycle.apply(
                new BikeCreatedEvent(
                        command.getId(),
                        command.getName(),
                        command.getDescription(),
                        command.getCreatedAt()
                ));
    }


    @EventSourcingHandler
    public void on(BikeCreatedEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.createdAt = event.getCreatedAt();
    }


    @CommandHandler
    public void handle(BikeDeleteCommand command){
        AggregateLifecycle.apply(new BikeDeletedEvent(command.getId()
        ));
    }


    @EventSourcingHandler
    public void on(BikeDeletedEvent event){
        AggregateLifecycle.markDeleted();
    }


    @CommandHandler
    public void handle(BikeUpdateCommand command){
        AggregateLifecycle.apply(new BikeUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription()
        ));
    }

    @EventSourcingHandler
    public void on(BikeUpdatedEvent event){
        this.name = event.getName();
        this.description = event.getDescription();
    }


    @CommandHandler
    public void handle(BikeRentCommand command){
        AggregateLifecycle.apply(new BikeRentedEvent(
                command.getId(),
                command.getRenterId()
        ));
    }

    @EventSourcingHandler
    public void on(BikeRentedEvent event){
        this.renterId = event.getRenterId();
        this.rented = Boolean.TRUE;
    }

    @CommandHandler
    public void handle(BikeReturnCommand command){
        AggregateLifecycle.apply(new BikeReturnedEvent(
                command.getRenterId(),
                command.getRentalMileAge()
        ));
    }

    @EventSourcingHandler
    public void on(BikeReturnedEvent event){
        this.mileAge += event.getRentalMileAge();
        this.rented = Boolean.FALSE;
    }
}
