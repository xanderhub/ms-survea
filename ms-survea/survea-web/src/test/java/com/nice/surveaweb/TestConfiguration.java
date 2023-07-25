package com.nice.surveaweb;

import com.nice.survea.reply.gateway.ReplyGateway;
import com.nice.survea.reply.gateway.ReplyGatewayFake;
import com.nice.survea.survey.gateway.SurveyGateway;
import com.nice.survea.survey.gateway.SurveyGatewayFake;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.gateway.TemplateGatewayFake;
import com.nice.surveaweb.api.ReplyApi;
import com.nice.surveaweb.api.SurveyApi;
import com.nice.surveaweb.api.TemplateApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;

@Configuration
public class TestConfiguration {

    @Bean
    public TestContext context() {
        return new TestContext();
    }

    @Bean
    public TemplateApi templateApi(MockMvc mockMvc) {
        return new TemplateApi(mockMvc);
    }

    @Bean
    public TemplateGateway templateGateway() {
        return new TemplateGatewayFake();
    }

    @Bean
    public SurveyApi surveyApi(MockMvc mockMvc) {
        return new SurveyApi(mockMvc);
    }

    @Bean
    public SurveyGateway surveyGatewayFake() {
        return new SurveyGatewayFake();
    }

    @Bean
    public ReplyApi replyApi(MockMvc mockMvc) { return new ReplyApi(mockMvc); }

    @Bean
    public ReplyGateway replyGatewayFake() { return new ReplyGatewayFake(); }
}

