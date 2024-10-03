package com.gitgudgang.fakeperson.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gitgudgang.fakeperson.entity.NameGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameGenderListWrapper {
    @JsonProperty("persons")
    private List<NameGender> entries;
}
