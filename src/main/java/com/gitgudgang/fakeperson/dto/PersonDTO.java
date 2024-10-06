package com.gitgudgang.fakeperson.dto;

import java.time.LocalDate;

public class PersonDTO {
    public sealed interface PartialPersonData permits CprDTO, NameCprDTO, NameCprDobDTO, CprNameGenderDTO, CprNameGenderDobDTO, AddressDTO, PhoneDTO, FullPersonDTO {}
    public record CprDTO(String cpr) implements PartialPersonData {}
    public record NameCprDTO(String firstName, String lastName, String cpr) implements PartialPersonData {}

    public record NameCprDobDTO(String firstName, String lastName, String cpr,
                                LocalDate dateOfBirth) implements PartialPersonData {
    }
    public record CprNameGenderDTO(String cpr, String firstName, String lastName, String gender) implements PartialPersonData {}

    public record CprNameGenderDobDTO(String cpr, String firstName, String lastName, String gender,
                                      LocalDate dateOfBirth) implements PartialPersonData {
    }
    public record AddressDTO(
            String street,
            int houseNumber,
            String floor,
            String door,
            int postalCode,
            String town
    ) implements PartialPersonData {}
    public record PhoneDTO(int phoneNumber) implements PartialPersonData {}
    public record FullPersonDTO(
            String cpr,
            String firstName,
            String lastName,
            String gender,
            LocalDate dateOfBirth,
            AddressDTO address,
            int phoneNumber
    ) implements PartialPersonData {}
}
