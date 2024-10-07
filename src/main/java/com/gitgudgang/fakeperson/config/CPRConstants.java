package com.gitgudgang.fakeperson.config;

import java.time.LocalDate;
import java.util.List;

public interface CPRConstants {
    LocalDate CPR_REGISTER_START_DATE = LocalDate.of(1924, 1, 1);
    List<Integer> CENTURY_CODES_1900 = List.of(0, 1, 2, 3);
    List<Integer> CENTURY_CODES_2000 = List.of(4, 5, 6, 7);
    List<Integer> GENDER_CODES_FEMALE = List.of(0, 2, 4, 6, 8);
    List<Integer> GENDER_CODES_MALE = List.of(1, 3, 5, 7, 9);
    LocalDate VALID_DOB = LocalDate.of(1980, 1, 1);

}
