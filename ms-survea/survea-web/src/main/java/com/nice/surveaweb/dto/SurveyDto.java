package com.nice.surveaweb.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class SurveyDto {
    private String name;
    private String templateName;
    private List<QuestionDto> questions;
}
