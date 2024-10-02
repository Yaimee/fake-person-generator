package com.gitgudgang.fakeperson.dto;

import java.time.LocalDate;

public class PersonDTOs {
    public record CprDto(String cpr) {}
    public record NameCprDto(String firstName, String lastName, String cpr) {}
    public record NameCprDobDto(String firstName, String lastName, String cpr, LocalDate dob) {}
    public record PersonDto(
            String cpr,
            String firstName,
            String lastName,
            String gender,
            LocalDate dob,
            AddressDto address,
            String phoneNumber
    ) {}
    public record AddressDto(
            String street,
            String houseNumber,
            String apartment,
            String postalCode,
            String townName
    ) {}
    public record PhoneDto(String phoneNumber) {}
}
