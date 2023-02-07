package com.example.axontest.renter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Renter {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
}
