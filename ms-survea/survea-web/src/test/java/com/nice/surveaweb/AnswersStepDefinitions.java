package com.nice.surveaweb;

import com.nice.surveaweb.api.ReplyApi;
import com.nice.surveaweb.dto.AnswerDto;
import com.nice.surveaweb.dto.ReplyResponse;
import com.nice.surveaweb.util.Result;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnswersStepDefinitions {

    private final ReplyApi replyApi;
    private final TestContext testContext;
    private Result<ReplyResponse> result;

    public AnswersStepDefinitions(ReplyApi replyApi, TestContext testContext) {
        this.replyApi = replyApi;
        this.testContext = testContext;
    }

    @When("Jimmy answers with {word} to next question in survey")
    public void jimmyAnswersToQuestion(String text) {
        testContext.getReply().getAnswers().add(new AnswerDto(text));
    }

    @When("Jimmy submits a reply")
    public void jimmyCreatesReply() {
        result = replyApi.createReply(testContext.getReply());
        testContext.setErrorDto(result.getError());
    }

    @Then("Miranda can see answers in the survey")
    public void mirandaCanSeeAnswersInTheSurvey() {
        result = replyApi.getReply(testContext.getSurveyDto().getName());
        assertNotNull(result.getResult());
        assertEquals(testContext.getReply().getAnswers(), result.getResult().getReplyDto().getAnswers());
    }

//    @Then("Jimmy receives {word} error")
//    public void jimmyReceivesError(String errorCode) {
//        assertNotNull(testContext.getErrorDto());
//        assertEquals(errorCode, testContext.getErrorDto().getCode());
//    }
}
