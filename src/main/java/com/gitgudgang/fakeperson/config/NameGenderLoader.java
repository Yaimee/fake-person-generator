package com.gitgudgang.fakeperson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitgudgang.fakeperson.repository.NameGenderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Slf4j
@Component
public class NameGenderLoader implements ApplicationRunner {
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;
    private final NameGenderRepository repository;
    private static final String filePath = "classpath:person-names.json";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Loading names and gender");

        NameGenderListWrapper wrapper = objectMapper.readValue(readJson(), NameGenderListWrapper.class);
        repository.saveAll(wrapper.getEntries());

        log.info("Loaded {} names and gender", wrapper.getEntries().size());
    }

    private String readJson() throws IOException {
        Resource resource = resourceLoader.getResource(NameGenderLoader.filePath);
        return resource.getContentAsString(StandardCharsets.UTF_8);
    }
}

