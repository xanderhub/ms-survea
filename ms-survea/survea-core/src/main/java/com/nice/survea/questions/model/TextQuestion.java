package com.nice.survea.questions.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextQuestion extends Question {
    private String text;

    public TextQuestion(String text) {
        super("Text");
        this.text = text;
    }
}
