package com.nice.surveaweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RangeQuestionDto extends QuestionDto {
    private String question;
    private int minValue;
    private int maxValue;
}
