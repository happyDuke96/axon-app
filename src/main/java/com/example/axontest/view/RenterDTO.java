package com.example.axontest.view;

import lombok.Value;

import java.util.UUID;

@Value
public class RenterDTO {
    UUID id;
    String firstName;
    String lastName;
    Integer age;
}
