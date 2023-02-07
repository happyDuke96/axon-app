package com.example.axontest.renter.event;

import lombok.Value;

import java.util.UUID;

@Value
public class RenterUpdatedEvent {

    UUID id;
    String firstName;
    Integer age;
}
