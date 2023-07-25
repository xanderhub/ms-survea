package com.nice.surveaweb;

import com.nice.survea.reply.gateway.ReplyGateway;
import com.nice.survea.reply.usecase.ReplyInteractor;
import com.nice.survea.reply.usecase.ReplyUseCase;
import com.nice.survea.survey.gateway.SurveyGateway;
import com.nice.survea.survey.usecase.SurveyInteractor;
import com.nice.survea.survey.usecase.SurveyUseCase;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.usecase.TemplateInteractor;
import com.nice.survea.template.usecase.TemplateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveaConfiguration {

    @Bean
    public TemplateUseCase templateUseCase(TemplateGateway templateGateway) {
        return new TemplateInteractor(templateGateway);
    }

    @Bean
    public SurveyUseCase surveyUseCase(SurveyGateway surveyGateway, TemplateGateway templateGateway) {
        return new SurveyInteractor(surveyGateway, templateGateway);
    }

    @Bean
    public ReplyUseCase replyUseCase(ReplyGateway replyGateway, SurveyUseCase surveyUseCase) {
        return new ReplyInteractor(replyGateway, surveyUseCase);
    }
}
