package com.nice.surveaweb;

import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.model.Template;
import com.nice.surveaweb.api.TemplateApi;
import com.nice.surveaweb.dto.TemplateResponse;
import com.nice.surveaweb.util.Result;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateStepDefinitions {

    private final TemplateApi templateApi;
    private final TemplateGateway templateGateway;
    private final TestContext testContext;
    private Result<TemplateResponse> result;

    public TemplateStepDefinitions(TemplateApi templateApi, TemplateGateway templateGateway, TestContext testContext) {
        this.templateApi = templateApi;
        this.templateGateway = templateGateway;
        this.testContext = testContext;
    }

    @Given("Miranda has template with name {string}")
    public void mirandaHasTemplateWithName(String templateName) {
        testContext.setTemplate(templateGateway.createTemplate(new Template(templateName)));
    }

    @When("Miranda creates template with name {string}")
    public void mirandaCreatesTemplateWithName(String templateName) {
        result = templateApi.createEmptyTemplate(templateName);
        testContext.setErrorDto(result.getError());
    }

    @Then("Miranda can see template {string} in her account")
    public void mirandaCanSeeTemplateInHerAccount(String templateName) {
        Result<TemplateResponse> response = templateApi.getByName(templateName);

        assertNull(response.getError());
        assertEquals(templateName, response.getResult().getTemplate().getName());
    }

    @Then("Template created successfully")
    public void templateCreatedSuccessfully() {
        assertNull(result.getError());
    }
}
