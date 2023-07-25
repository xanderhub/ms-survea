package com.nice.survea.template.gateway;

import com.nice.survea.template.model.Template;

import java.util.Optional;

public interface TemplateGateway {
    Template createTemplate(Template template);
    Optional<Template> getByName(String templateName);
    boolean exists(String templateName);
    Template update(Template template);
}
