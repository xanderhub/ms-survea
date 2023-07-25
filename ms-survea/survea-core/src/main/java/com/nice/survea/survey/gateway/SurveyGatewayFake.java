package com.nice.survea.survey.gateway;

import com.nice.survea.survey.model.Survey;

import java.util.HashMap;
import java.util.Map;

public class SurveyGatewayFake implements SurveyGateway {

    Map<String, Survey> surveys = new HashMap<>();

    @Override
    public Survey create(Survey survey) {
        surveys.put(survey.getName(), survey);
        return survey;
    }

    @Override
    public Survey getByName(String surveyName) {
        return surveys.get(surveyName);
    }
}
