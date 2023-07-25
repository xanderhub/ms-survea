package com.nice.surveaweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TextQuestionDto extends QuestionDto {
    private String questionText;
}
