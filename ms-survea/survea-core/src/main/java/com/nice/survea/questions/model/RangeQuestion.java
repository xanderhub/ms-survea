package com.nice.survea.questions.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RangeQuestion extends Question {
    private String text;
    private Integer min;
    private Integer max;

    public RangeQuestion(String text, int minValue, int maxValue) {
        super("Range");
        this.text = text;
        this.min = minValue;
        this.max = maxValue;
    }
}
