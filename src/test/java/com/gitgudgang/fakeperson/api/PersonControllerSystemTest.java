package com.gitgudgang.fakeperson.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * System test uses Spring to start application, including database, and uses Spring MockMVC to test REST endpoints
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PersonControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getCpr_shouldReturn10Digits() throws Exception {
        mockMvc.perform(get("/api/persons/cpr"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpr", matchesPattern("\\d{10}"))); // Assuming CPR is a 10-digit number
    }

    @Test
    void getPartialPersonData_withInvalidType_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/persons/invalidType"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBulkPersons_shouldReturnListOfPersons() throws Exception {
        int minCount = 1;
        mockMvc.perform(get("/api/persons/bulk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(minCount))));
    }
}