package com.gitgudgang.fakeperson.dto;

public enum PersonDtoType {
    CPR_DTO("cpr"),
    FIRST_NAME_LAST_NAME_CPR_DTO("fname-lname-cpr"),
    FIRST_NAME_LAST_NAME_CPR_DATE_OF_BIRTH_DTO("fname-lname-cpr-dob"),
    CPR_FIRST_NAME_LAST_NAME_GENDER_DTO("cpr-fname-lname-gender"),
    CPR_FIRST_NAME_LAST_NAME_GENDER_DATE_OF_BIRTH_DTO("cpr-fname-lname-gender-dob"),
    ADDRESS_DTO("address"),
    PHONE_DTO("phone"),
    SINGLE_PERSON_DTO("single"),
    BULK_PERSONS_DTO("bulk");

    private final String dtoTypeText;

    PersonDtoType(String dtoTypeText) {
        this.dtoTypeText = dtoTypeText;
    }

    public static PersonDtoType fromString(String text) {
        for (PersonDtoType type : PersonDtoType.values()) {
            if (type.dtoTypeText.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}