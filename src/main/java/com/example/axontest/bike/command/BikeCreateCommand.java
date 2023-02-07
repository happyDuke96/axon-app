package com.example.axontest.bike.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;
import java.util.UUID;

@Value
public class BikeCreateCommand {
    @TargetAggregateIdentifier
    UUID id;
    String name;
    String description;
    Instant createdAt;
}
