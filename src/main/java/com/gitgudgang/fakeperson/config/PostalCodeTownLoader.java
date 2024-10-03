package com.gitgudgang.fakeperson.config;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Slf4j
@Component
public class PostalCodeTownLoader implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Loading postal codes and towns");
        // Read the postal codes and towns

        // Initialise the repository

        // Register the bean with Spring
    }
}
