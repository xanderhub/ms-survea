package com.nice.surveaweb.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextQuestionDto.class, name = "Text"),
        @JsonSubTypes.Type(value = RangeQuestionDto.class, name = "Range"),
})
public abstract class QuestionDto {
}
