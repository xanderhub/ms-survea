package com.nice.survea.template.usecase;

import com.nice.survea.template.model.Template;

import java.util.Optional;

public interface TemplateUseCase {
    Template createEmptyTemplate(String name);
    Optional<Template> getByName(String templateName);
    Template update(Template template);
}
