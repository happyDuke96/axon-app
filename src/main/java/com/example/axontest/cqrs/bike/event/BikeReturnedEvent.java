package com.example.axontest.cqrs.bike.event;

import lombok.Value;

import java.util.UUID;

@Value
public class BikeReturnedEvent {
    UUID id;
    Double rentalMileAge; // пробег аренды
}
