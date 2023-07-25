package com.nice.survea.template.usecase;

import com.nice.survea.questions.model.RangeQuestion;
import com.nice.survea.template.exceptions.WrongRangeQuestionException;
import com.nice.survea.template.exceptions.InvalidTemplateNameException;
import com.nice.survea.template.exceptions.TemplateAlreadyExistsException;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.gateway.TemplateGatewayFake;
import com.nice.survea.template.model.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TemplateInteractorTests {

    private TemplateInteractor templateInteractor;
    private TemplateGateway templateGateway;

    @BeforeEach
    void setUp() {
        templateGateway = new TemplateGatewayFake();
        templateInteractor = new TemplateInteractor(templateGateway);
    }

    @Test
    public void createShouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> templateInteractor.createEmptyTemplate(null));
    }

    @Test
    public void createShouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> templateInteractor.createEmptyTemplate(""));
    }

    @Test
    public void getShouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> templateInteractor.getByName(null));
    }

    @Test
    public void getShouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> templateInteractor.getByName(""));
    }

    @Test
    public void getShouldReturnEmptyWhenTemplateDoesNotExist() {
        Optional<Template> result = templateInteractor.getByName("NonExistingTemplate");
        assertEquals(Optional.empty(), result);
    }


    @Test
    public void createShouldThrowExceptionIfTemplateAlreadyExists() {
        templateGateway.createTemplate(new Template("ExistingTemplate"));

        assertThrows(TemplateAlreadyExistsException.class, () -> templateInteractor.createEmptyTemplate("ExistingTemplate"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Wrong!", "$Wrong123", "@332", "(wrong)", "#wrong", "wrong_wrong"})
    public void createShouldThrowExceptionWhenTemplateNameIsWrong(String templateName) {
        assertThrows(InvalidTemplateNameException.class, () -> templateInteractor.createEmptyTemplate(templateName));
    }

    @Test
    public void updateShouldThrowExceptionWhenTemplateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> templateInteractor.update(null));
    }

    @Test
    public void updateShouldThrowExceptionIfTemplateHasWrongRangeQuestion() {
        Template template = templateGateway.createTemplate(new Template("NewTemplate"));
        template.getQuestions().add(new RangeQuestion("WrongRangeQuestion", 10, 9));

        assertThrows(WrongRangeQuestionException.class, () -> templateInteractor.update(template));
    }

    @Test
    public void updateShouldThrowExceptionIfTemplateHasRangeQuestionWithoutValues() {
        RangeQuestion rangeQuestion = new RangeQuestion("RangeQuestionWithoutValues", 1, 10);
        rangeQuestion.setMax(null);
        rangeQuestion.setMin(null);

        Template template = templateGateway.createTemplate(new Template("NewTemplate"));
        template.getQuestions().add(rangeQuestion);

        assertThrows(WrongRangeQuestionException.class, () -> templateInteractor.update(template));
    }
}
