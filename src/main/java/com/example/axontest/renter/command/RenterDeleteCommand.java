package com.example.axontest.renter.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class RenterDeleteCommand {
    @TargetAggregateIdentifier
    UUID id;
}
