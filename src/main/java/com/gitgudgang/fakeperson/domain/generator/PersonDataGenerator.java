package com.gitgudgang.fakeperson.domain.generator;

import com.gitgudgang.fakeperson.entity.NameGender;
import com.gitgudgang.fakeperson.repository.NameGenderRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import static com.gitgudgang.fakeperson.config.CPRConstants.*;


@NoArgsConstructor
@Getter
@Setter
@Service
public class PersonDataGenerator {

    private final Random random = new Random();
    private NameGenderRepository nameGenderRepository;

    @Autowired
    public PersonDataGenerator(NameGenderRepository nameGenderRepository) {
        this.nameGenderRepository = nameGenderRepository;
    }

    public String generateCpr(String gender, LocalDate dob) {
        if (!isValidDOB(dob)) {
            throw new IllegalArgumentException("Invalid date of birth");
        }

        String dateString = dob.format(DateTimeFormatter.ofPattern("ddMMyy"));
        String sequenceNumber = generateSequenceNumber(gender, dob);
        return dateString + sequenceNumber;
    }

    private static boolean isValidDOB(LocalDate dob) {
        return dob.isAfter(CPR_REGISTER_START_DATE.minusDays(1)) && dob.isBefore(LocalDate.now());
    }

    private String generateSequenceNumber(String gender, LocalDate dob) {
        int firstDigit = generateFirstDigitOfSequence(dob);

        StringBuilder sequence = new StringBuilder(String.valueOf(firstDigit));
        for (int i = 0; i < 2; i++) {
            sequence.append(random.nextInt(10));
        }

        int lastDigit = generateLastDigitOfSequence(gender);

        return sequence.append(lastDigit).toString();
    }

    private int generateFirstDigitOfSequence(LocalDate dob) {
        int century = dob.getYear() / 100;
        return switch (century) {
            case 19 -> CENTURY_CODES_1900.get(random.nextInt(CENTURY_CODES_1900.size()));
            case 20 -> CENTURY_CODES_2000.get(random.nextInt(CENTURY_CODES_2000.size()));
            default -> throw new IllegalArgumentException("Unsupported century");
        };
    }

    private int generateLastDigitOfSequence(String gender) {
        return gender.equalsIgnoreCase("female") ?
                random.nextInt(5) * 2 : random.nextInt(5) * 2 + 1;
    }

    public LocalDate generateDateOfBirth() {
        var minDay = CPR_REGISTER_START_DATE.toEpochDay();
        var maxDay = LocalDate.now().toEpochDay();
        return LocalDate.ofEpochDay(minDay + random.nextLong(maxDay - minDay + 1));
    }

    public Optional<NameGender> generatePersonBaseData() {
        var index = random.nextInt(nameGenderRepository.getAllUUIDs().size());
        var id = nameGenderRepository.getAllUUIDs().get(index);
        return nameGenderRepository.findById(id);
    }
}