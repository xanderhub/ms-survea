package com.nice.surveaweb.api;

import com.nice.surveaweb.dto.ReplyDto;
import com.nice.surveaweb.dto.ReplyRequest;
import com.nice.surveaweb.dto.ReplyResponse;
import com.nice.surveaweb.util.Result;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ReplyApi extends BaseApi {

    private final MockMvc mockMvc;

    public ReplyApi(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public Result<ReplyResponse> createReply(ReplyDto replyDto) {
        ReplyRequest replyRequest = new ReplyRequest(replyDto);
        try {
            String body = toJson(replyRequest);
            MvcResult result = mockMvc.perform(post("/reply")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andReturn();
            return readResult(result, ReplyResponse.class);

        } catch (Exception exception) {
            throw new RuntimeException("Failed to create reply on survey " + replyDto.getSurveyName(), exception);
        }
    }

    public Result<ReplyResponse> getReply(String surveyName) {
        try {
            MvcResult result = mockMvc.perform(get("/reply/" + surveyName))
                    .andReturn();
            return readResult(result, ReplyResponse.class);
        } catch (Exception exception) {
            throw new RuntimeException("Failed to get reply of survey " + surveyName, exception);
        }
    }
}
