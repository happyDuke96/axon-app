package com.example.axontest.bike.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Bike {

    @Id
    private UUID id;
    private UUID renterId;
    private String name;
    private String description;
    private Instant createdAt;
    private Boolean rented = Boolean.FALSE;
    private Double mileAge;
}
