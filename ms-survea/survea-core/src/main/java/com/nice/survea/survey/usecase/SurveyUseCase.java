package com.nice.survea.survey.usecase;

import com.nice.survea.survey.model.Survey;

public interface SurveyUseCase {
    Survey create(String surveyName, String templateName);
    Survey getByName(String name);
}
