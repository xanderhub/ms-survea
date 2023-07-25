package com.nice.surveaweb.controller;

import com.nice.survea.survey.exceptions.NoQuestionsInTemplateException;
import com.nice.survea.survey.model.Survey;
import com.nice.survea.survey.usecase.SurveyUseCase;
import com.nice.surveaweb.adapters.DtoAdapter;
import com.nice.surveaweb.dto.ErrorDto;
import com.nice.surveaweb.dto.SurveyRequest;
import com.nice.surveaweb.dto.SurveyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyUseCase surveyUseCase;

    public SurveyController(SurveyUseCase surveyUseCase) {
        this.surveyUseCase = surveyUseCase;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SurveyRequest request) {
        try {
            Survey survey = surveyUseCase.create(request.getSurveyName(), request.getTemplateName());
            return new ResponseEntity(new SurveyResponse(DtoAdapter.toSurveyDto(survey)), HttpStatus.CREATED);
        } catch (NoQuestionsInTemplateException exception) {
            return new ResponseEntity(new ErrorDto("NoQuestionError"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable(value = "name") String name) {
        Survey survey = surveyUseCase.getByName(name);
        return ResponseEntity.ok(new SurveyResponse(DtoAdapter.toSurveyDto(survey)));
    }
}
