package com.example.axontest.aggregate;

import com.example.axontest.cqrs.renter.command.RenterCreateCommand;
import com.example.axontest.cqrs.renter.command.RenterDeleteCommand;
import com.example.axontest.cqrs.renter.command.RenterUpdateCommand;
import com.example.axontest.cqrs.renter.event.RenterCreatedEvent;
import com.example.axontest.cqrs.renter.event.RenterDeletedEvent;
import com.example.axontest.cqrs.renter.event.RenterUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class RenterAggregate {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;


    public RenterAggregate() {
        // no-arg constructor, which is required by Axon.
    }

    @CommandHandler
    public RenterAggregate(RenterCreateCommand command){
        AggregateLifecycle.apply(new RenterCreatedEvent(
                command.getId(),
                command.getFirstName(),
                command.getLastName(),
                command.getAge()
        ));
    }

    @EventSourcingHandler
    public void on(RenterCreatedEvent event){
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.age = event.getAge();
    }


    @CommandHandler
    public void hande(RenterUpdateCommand command){
        AggregateLifecycle.apply(new RenterUpdatedEvent(
                command.getId(),
                command.getFirstName(),
                command.getAge()
        ));
    }

    @EventSourcingHandler
    public void on(RenterUpdatedEvent event){
        this.firstName = event.getFirstName();
        this.age = event.getAge();
    }


    @CommandHandler
    public void handle(RenterDeleteCommand command){
        AggregateLifecycle.apply(new RenterDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(RenterDeletedEvent event){
        AggregateLifecycle.markDeleted();
    }
}
