package com.nice.surveaweb;

import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommonStepDefinitions {

    private final TestContext testContext;

    public CommonStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("{word} receives {word} error")
    public void userReceivesError(String user, String errorCode) {
        assertNotNull(testContext.getErrorDto());
        assertEquals(errorCode, testContext.getErrorDto().getCode());
    }
}
