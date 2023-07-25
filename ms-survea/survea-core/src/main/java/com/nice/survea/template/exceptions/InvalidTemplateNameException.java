package com.nice.survea.template.exceptions;

import lombok.Getter;

@Getter
public class InvalidTemplateNameException extends RuntimeException {

    private final String name;

    public InvalidTemplateNameException(String name) {
        this.name = name;
    }
}
