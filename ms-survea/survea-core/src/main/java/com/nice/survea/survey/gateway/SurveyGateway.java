package com.nice.survea.survey.gateway;

import com.nice.survea.survey.model.Survey;

public interface SurveyGateway {
    Survey create(Survey survey);
    Survey getByName(String surveyName);
}
