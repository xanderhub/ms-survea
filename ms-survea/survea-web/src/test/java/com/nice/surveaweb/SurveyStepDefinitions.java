package com.nice.surveaweb;

import com.nice.survea.reply.model.Reply;
import com.nice.surveaweb.adapters.DtoAdapter;
import com.nice.surveaweb.api.SurveyApi;
import com.nice.surveaweb.dto.ReplyDto;
import com.nice.surveaweb.dto.SurveyResponse;
import com.nice.surveaweb.dto.TemplateDto;
import com.nice.surveaweb.util.Result;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SurveyStepDefinitions {

    private final SurveyApi surveyApi;
    private final TestContext testContext;
    private Result<SurveyResponse> result;

    public SurveyStepDefinitions(SurveyApi surveyApi, TestContext testContext) {
        this.surveyApi = surveyApi;
        this.testContext = testContext;
    }

    @Given("Jimmy retrieves survey with name {string}")
    public void jimmyRetrievesSurveyWithName(String surveyName) {
        result = surveyApi.getByName(surveyName);
        testContext.setReply(new ReplyDto(surveyName, new ArrayList<>()));
    }

    @When("Miranda creates survey with name {string}")
    public void mirandaCreatesSurveyWithName(String surveyName) {
        result = surveyApi.createSurvey(surveyName, testContext.getTemplate().getName());
        if (result.getResult() != null)
            testContext.setSurveyDto(result.getResult().getSurvey());
        testContext.setErrorDto(result.getError());
    }

    @Then("Survey contains questions from template")
    public void surveyContainsQuestionsFromTemplate() {
        Result<SurveyResponse> surveyResult = surveyApi.getByName(result.getResult().getSurvey().getName());
        TemplateDto templateDto = DtoAdapter.toTemplateDto(testContext.getTemplate());

        assertNull(surveyResult.getError());
        assertEquals(templateDto.getQuestions(), surveyResult.getResult().getSurvey().getQuestions());
    }
}
