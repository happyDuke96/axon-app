package com.example.axontest.view;

import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class BikeDTO {
    UUID id;
    UUID renterId;
    String name;
    String description;
    Instant createdAt;
    Boolean rented;
    Double mileAge;
}
