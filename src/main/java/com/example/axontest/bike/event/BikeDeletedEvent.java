package com.example.axontest.bike.event;

import lombok.Value;

import java.util.UUID;

@Value
public class BikeDeletedEvent {
    UUID id;
}
