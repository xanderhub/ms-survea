package com.nice.survea.survey.usecase;

import com.nice.survea.survey.exceptions.NoQuestionsInTemplateException;
import com.nice.survea.survey.gateway.SurveyGateway;
import com.nice.survea.survey.model.Survey;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.model.Template;

public class SurveyInteractor implements SurveyUseCase{

    private final SurveyGateway surveyGateway;
    private final TemplateGateway templateGateway;

    public SurveyInteractor(SurveyGateway surveyGateway, TemplateGateway templateGateway) {
        this.surveyGateway = surveyGateway;
        this.templateGateway = templateGateway;
    }

    @Override
    public Survey create(String surveyName, String templateName) {
        if (surveyName == null || surveyName.isEmpty() || templateName == null || templateName.isEmpty())
            throw new IllegalArgumentException(surveyName + " " + templateName);

        Template template = templateGateway.getByName(templateName).get();

        if (template.getQuestions() == null || template.getQuestions().isEmpty())
            throw new NoQuestionsInTemplateException();

        Survey survey = new Survey(surveyName, templateName, template.getQuestions());
        survey = surveyGateway.create(survey);

        return survey;
    }

    @Override
    public Survey getByName(String surveyName) {
        if (surveyName == null || surveyName.isEmpty())
            throw new IllegalArgumentException(surveyName);

        return surveyGateway.getByName(surveyName);
    }
}
