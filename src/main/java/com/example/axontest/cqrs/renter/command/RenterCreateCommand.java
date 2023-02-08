package com.example.axontest.cqrs.renter.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class RenterCreateCommand {

    @TargetAggregateIdentifier
    UUID id;
    String firstName;
    String lastName;
    Integer age;
}
