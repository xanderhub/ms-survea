package com.nice.surveaweb.api;

import com.nice.surveaweb.dto.SurveyRequest;
import com.nice.surveaweb.dto.SurveyResponse;
import com.nice.surveaweb.util.Result;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class SurveyApi extends BaseApi {

    private final MockMvc mockMvc;

    public SurveyApi(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public Result<SurveyResponse> createSurvey(String surveyName, String templateName) {
        SurveyRequest surveyRequest = new SurveyRequest(surveyName, templateName);
        try {
            String body = toJson(surveyRequest);
            MvcResult result = mockMvc.perform(post("/survey")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andReturn();
            return readResult(result, SurveyResponse.class);

        } catch (Exception exception) {
            throw new RuntimeException("Failed to create survey", exception);
        }
    }


    public Result<SurveyResponse> getByName(String name) {
        try {
            MvcResult result = mockMvc.perform(get("/survey/" + name))
                    .andReturn();
            return readResult(result, SurveyResponse.class);
        } catch (Exception exception) {
            throw new RuntimeException("Failed to get survey " + name, exception);
        }
    }
}
