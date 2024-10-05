package com.gitgudgang.fakeperson.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class NameGender {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "surname")
    private String lastName;

    private String gender;
}
