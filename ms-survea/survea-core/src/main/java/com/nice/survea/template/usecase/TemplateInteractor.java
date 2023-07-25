package com.nice.survea.template.usecase;

import com.nice.survea.questions.model.RangeQuestion;
import com.nice.survea.template.exceptions.WrongRangeQuestionException;
import com.nice.survea.template.exceptions.InvalidTemplateNameException;
import com.nice.survea.template.exceptions.TemplateAlreadyExistsException;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.model.Template;
import com.nice.survea.util.Validators;

import java.util.Optional;

public class TemplateInteractor implements TemplateUseCase {

    private TemplateGateway templateGateway;

    public TemplateInteractor(TemplateGateway templateGateway) {
        this.templateGateway = templateGateway;
    }

    @Override
    public Template createEmptyTemplate(String templateName) {
        if (templateName == null || templateName.isEmpty())
            throw new IllegalArgumentException(templateName);

        if (!Validators.validateName(templateName))
            throw new InvalidTemplateNameException(templateName);

        if (templateGateway.exists(templateName))
            throw new TemplateAlreadyExistsException(templateName);

        Template template = new Template(templateName);

        templateGateway.createTemplate(template);
        return template;
    }

    @Override
    public Optional<Template> getByName(String templateName) {
        if (templateName == null || templateName.isEmpty()) {
            throw new IllegalArgumentException(templateName);
        }

        return templateGateway.getByName(templateName);
    }

    @Override
    public Template update(Template template) {
        if (template == null) {
            throw new IllegalArgumentException("template");
        }

        validateTemplate(template);

        if (templateGateway.exists(template.getName())) {
            return templateGateway.update(template);
        }

        return templateGateway.createTemplate(template);
    }

    private void validateTemplate(Template template) {
        boolean allRangeQuestionsValid =
                template.getQuestions()
                        .stream().filter(q -> q.getType().equals("Range"))
                        .map(RangeQuestion.class::cast)
                        .allMatch(rangeQuestion ->
                                rangeQuestion.getMin() != null &&
                                rangeQuestion.getMax() != null &&
                                rangeQuestion.getMin() < rangeQuestion.getMax());

        if(!allRangeQuestionsValid)
            throw new WrongRangeQuestionException();
    }
}
