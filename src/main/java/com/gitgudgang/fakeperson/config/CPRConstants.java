package com.gitgudgang.fakeperson.config;

import java.time.LocalDate;
import java.util.List;

public interface CPRConstants {
    LocalDate EARLIEST_VALID_DATE = LocalDate.of(1924, 1, 1);
    List<Integer> CENTURY_CODE_1900 = List.of(0, 1, 2, 3);
    List<Integer> CENTURY_CODE_2000 = List.of(4, 5, 6, 7);
}
