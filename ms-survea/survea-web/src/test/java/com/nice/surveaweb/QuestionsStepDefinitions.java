package com.nice.surveaweb;

import com.nice.survea.questions.model.RangeQuestion;
import com.nice.survea.questions.model.TextQuestion;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.model.Template;
import com.nice.surveaweb.adapters.DtoAdapter;
import com.nice.surveaweb.api.TemplateApi;
import com.nice.surveaweb.dto.*;
import com.nice.surveaweb.util.Result;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QuestionsStepDefinitions {

    private final TemplateApi templateApi;
    private final TestContext testContext;
    private final TemplateGateway templateGateway;
    private Result<TemplateResponse> result;
    private TextQuestionDto textQuestionDto;
    private RangeQuestionDto rangeQuestionDto;

    public QuestionsStepDefinitions(TemplateApi templateApi, TestContext testContext, TemplateGateway templateGateway) {
        this.templateApi = templateApi;
        this.testContext = testContext;
        this.templateGateway = templateGateway;
    }

    @When("Miranda adds text question {string}")
    public void mirandaAddsTextQuestion(String question) {
        textQuestionDto = new TextQuestionDto(question);
        TemplateDto templateDto = DtoAdapter.toTemplateDto(testContext.getTemplate());
        templateDto.getQuestions().add(new TextQuestionDto(question));
        result = templateApi.updateTemplate(testContext.getTemplate().getName(),
                new TemplateRequest(testContext.getTemplate().getName(), templateDto));
    }

    @When("Miranda adds range question {string} with min value {int} and max value {int}")
    public void mirandaAddsRangeQuestion(String text,int min, int max) {
        rangeQuestionDto = new RangeQuestionDto(text, min, max);
        TemplateDto templateDto = DtoAdapter.toTemplateDto(testContext.getTemplate());
        templateDto.getQuestions().add(new RangeQuestionDto(text, min, max));
        result = templateApi.updateTemplate(testContext.getTemplate().getName(),
                new TemplateRequest(testContext.getTemplate().getName(), templateDto));
        testContext.setErrorDto(result.getError());
    }

    @Then("Miranda can see {int} {word} questions in the template")
    public void mirandaCanSeeNumberOfQuestionsInTheTemplate(int number, String type) {
        Result<TemplateResponse> templateResponseResult = templateApi
                .getByName(testContext.getTemplate().getName());
        assertNull(templateResponseResult.getError());
        TemplateDto templateDto = templateResponseResult.getResult().getTemplate();
        assertEquals(number, templateDto.getQuestions().size());
        QuestionDto expectedQuestion = type.equals("text") ? textQuestionDto : rangeQuestionDto;
        assertEquals(expectedQuestion, templateDto.getQuestions().get(0));
    }

    @Then("Template contains text question {string}")
    public void templateContainsTextQuestion(String question) {
        Template template = testContext.getTemplate();
        template.getQuestions().add(new TextQuestion(question));
        testContext.setTemplate(templateGateway.update(template));
    }

    @Then("Template contains range question {string} with min value {int} and max value {int}")
    public void templateContainsRangeQuestion(String question, Integer minValue, Integer maxValue) {
        Template template = testContext.getTemplate();
        template.getQuestions().add(new RangeQuestion(question, minValue, maxValue));
        testContext.setTemplate(templateGateway.update(template));
    }
}
