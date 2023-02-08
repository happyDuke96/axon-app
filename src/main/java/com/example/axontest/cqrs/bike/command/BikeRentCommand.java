package com.example.axontest.cqrs.bike.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class BikeRentCommand {
    @TargetAggregateIdentifier
    UUID id;
    UUID renterId;
}
