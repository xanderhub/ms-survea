package com.nice.surveaweb.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ReplyDto {
    private String surveyName;
    private List<AnswerDto> answers;

    public ReplyDto(String surveyName) {
        this(surveyName, new ArrayList<>());
    }
}
