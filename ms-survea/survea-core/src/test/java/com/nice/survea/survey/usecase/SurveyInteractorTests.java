package com.nice.survea.survey.usecase;

import com.nice.survea.survey.exceptions.NoQuestionsInTemplateException;
import com.nice.survea.survey.gateway.SurveyGatewayFake;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.gateway.TemplateGatewayFake;
import com.nice.survea.template.model.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SurveyInteractorTests {

    private SurveyInteractor surveyInteractor;
    private TemplateGateway templateGateway;

    @BeforeEach
    void setUp() {
        templateGateway = new TemplateGatewayFake();
        surveyInteractor = new SurveyInteractor(new SurveyGatewayFake(), templateGateway);
    }

    @Test
    public void createShouldThrowExceptionWhenSurveyNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> surveyInteractor.create(null, "NewTemplate"));
    }

    @Test
    public void createShouldThrowExceptionWhenSurveyNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> surveyInteractor.create("", "NewTemplate"));
    }

    @Test
    public void createShouldThrowExceptionWhenTemplateNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> surveyInteractor.create("NewSurvey", null));
    }

    @Test
    public void createShouldThrowExceptionWhenTemplateIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> surveyInteractor.create("NewSurvey", ""));
    }

    @Test
    public void getShouldThrowExceptionWhenSurveyNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> surveyInteractor.getByName(null));
    }

    @Test
    public void getShouldThrowExceptionWhenSurveyNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> surveyInteractor.getByName(""));
    }

    @Test
    public void createShouldThrowExceptionWhenTemplateWithoutQuestions() {
        templateGateway.createTemplate(new Template("WithoutQuestions"));
        assertThrows(NoQuestionsInTemplateException.class, () -> surveyInteractor.create("NewSurvey", "WithoutQuestions"));
    }
}
