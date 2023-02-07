package com.example.axontest.bike.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class BikeReturnCommand {
    @TargetAggregateIdentifier
    UUID renterId;
    Double rentalMileAge; // пробег аренды
}
