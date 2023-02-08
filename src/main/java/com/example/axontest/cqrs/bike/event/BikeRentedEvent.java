package com.example.axontest.cqrs.bike.event;

import lombok.Value;

import java.util.UUID;

@Value
public class BikeRentedEvent {
    UUID id;
    UUID renterId;
}
