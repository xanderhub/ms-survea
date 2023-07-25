package com.nice.survea.template.gateway;

import com.nice.survea.template.model.Template;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TemplateGatewayFake implements TemplateGateway {

    Map<String, Template> templateMap = new HashMap<>();

    @Override
    public Template createTemplate(Template template) {
        templateMap.put(template.getName(), template);
        return template;
    }

    @Override
    public Optional<Template> getByName(String templateName) {
        return Optional.ofNullable(templateMap.get(templateName));
    }

    @Override
    public boolean exists(String templateName) {
        return templateMap.containsKey(templateName);
    }

    @Override
    public Template update(Template template) {
        templateMap.put(template.getName(), template);
        return template;
    }
}
