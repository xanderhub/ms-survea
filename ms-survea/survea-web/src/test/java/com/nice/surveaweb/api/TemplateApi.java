package com.nice.surveaweb.api;

import com.nice.surveaweb.dto.TemplateRequest;
import com.nice.surveaweb.dto.TemplateResponse;
import com.nice.surveaweb.util.Result;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class TemplateApi extends BaseApi {

    private final MockMvc mockMvc;

    public TemplateApi(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public Result<TemplateResponse> createEmptyTemplate(String templateName) {

        TemplateRequest templateRequest = new TemplateRequest(templateName, null);

        try {
            String json = toJson(templateRequest);
            MvcResult result = mockMvc.perform(post("/template")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andReturn();
            return readResult(result, TemplateResponse.class);

        } catch (Exception exception) {
            throw new RuntimeException("Failed to create empty template", exception);
        }
    }

    public Result<TemplateResponse> getByName(String templateName) {
        try {
            MvcResult result = mockMvc.perform(get("/template/" + templateName)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            return readResult(result, TemplateResponse.class);

        } catch (Exception exception) {
            throw new RuntimeException("Failed to get template", exception);
        }
    }

    public Result<TemplateResponse> updateTemplate(String templateName, TemplateRequest templateRequest) {
        try {
            String json = toJson(templateRequest);
            MvcResult result = mockMvc.perform(put("/template/" + templateName)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andReturn();
            return readResult(result, TemplateResponse.class);
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update template", exception);
        }
    }
}
