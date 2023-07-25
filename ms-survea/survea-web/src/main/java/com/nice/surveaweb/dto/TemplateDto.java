package com.nice.surveaweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {
    private String name;
    private List<QuestionDto> questions;

    public TemplateDto(String name) {
        this(name, new ArrayList<>());
    }
}
