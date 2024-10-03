package com.gitgudgang.fakeperson.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
@Entity
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private String postalCode;
    private String town;
    private String street;
    private int houseNumber;
    private String floor;
    private String door;
}
