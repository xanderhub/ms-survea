package com.nice.survea.template.exceptions;

import lombok.Getter;

@Getter
public class TemplateAlreadyExistsException extends RuntimeException {

    private final String templateName;

    public TemplateAlreadyExistsException(String templateName) {
        this.templateName = templateName;
    }
}
