package com.gitgudgang.fakeperson.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class NameGender {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonProperty("name")
    private String firstName;

    @JsonProperty("surname")
    private String lastName;

    private String gender;

    public static NameGender create(String firstName, String lastName, String gender) {
        var nameGender = new NameGender();
        nameGender.setFirstName(firstName);
        nameGender.setLastName(lastName);
        nameGender.setGender(gender);
        return nameGender;
    }
}
