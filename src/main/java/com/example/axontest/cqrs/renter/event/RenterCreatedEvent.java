package com.example.axontest.cqrs.renter.event;

import lombok.Value;

import java.util.UUID;

@Value
public class RenterCreatedEvent {
    UUID id;
    String firstName;
    String lastName;
    Integer age;
}
