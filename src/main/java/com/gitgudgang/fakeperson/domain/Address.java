package com.gitgudgang.fakeperson.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Entity
public class Address {

    @Id
    @GeneratedValue
    private UUID id;
    private String street;
    private int houseNumber;
    private String floor;
    private String door;
    private int postalCode;
    private String town;
}
