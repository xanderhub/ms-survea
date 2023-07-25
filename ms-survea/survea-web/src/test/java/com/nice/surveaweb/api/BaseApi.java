package com.nice.surveaweb.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nice.surveaweb.dto.ErrorDto;
import com.nice.surveaweb.util.Result;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public abstract class BaseApi {
    protected ObjectMapper objectMapper;

    public BaseApi() {
        objectMapper = new ObjectMapper();
    }

    protected String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {

        }
        return null;
    }

    protected <T> T fromJson(String json, Class<T> c) {
        try {
            return (T) objectMapper.readValue(json, c);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    protected <T> Result<T> readResult(MvcResult result, Class<T> resultType) {
        String content = null;
        try {
            content = result.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {

        }
        if (isResultSuccessful(result)) {
            T resultDto = fromJson(content, resultType);
            return Result.ofResult(resultDto);
        } else {
            ErrorDto errorDto = fromJson(content, ErrorDto.class);
            return Result.ofError(errorDto);
        }
    }

    protected boolean isResultSuccessful(MvcResult result) {
        int statusCode = result.getResponse().getStatus();
        return statusCode == 200 || statusCode == 201;
    }
}
