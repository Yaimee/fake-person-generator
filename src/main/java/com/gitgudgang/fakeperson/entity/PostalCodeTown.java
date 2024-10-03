package com.gitgudgang.fakeperson.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class PostalCodeTown {
    @Id
    @GeneratedValue
    private UUID id;
}
