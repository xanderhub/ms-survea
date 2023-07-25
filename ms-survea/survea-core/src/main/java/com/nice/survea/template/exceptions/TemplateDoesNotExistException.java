package com.nice.survea.template.exceptions;

import lombok.Getter;

@Getter
public class TemplateDoesNotExistException extends RuntimeException {
    private final String templateName;

    public TemplateDoesNotExistException(String templateName) {
        super("Template " + templateName + " does not exist");
        this.templateName = templateName;
    }
}
