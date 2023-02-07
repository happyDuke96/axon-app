package com.example.axontest.bike.aggregate;

import com.example.axontest.bike.command.*;
import com.example.axontest.bike.event.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class BikeAggregate {

    private UUID id;
    private UUID renterId;
    private String name;
    private String description;
    private Instant createdAt;
    private Boolean rented;
    private Double mileAge;


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
        this.id = event.getId();
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
