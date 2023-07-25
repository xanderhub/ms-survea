package com.nice.surveaweb.controller;

import com.nice.survea.template.exceptions.InvalidTemplateNameException;
import com.nice.survea.template.exceptions.TemplateAlreadyExistsException;
import com.nice.survea.template.exceptions.WrongRangeQuestionException;
import com.nice.survea.template.model.Template;
import com.nice.survea.template.usecase.TemplateUseCase;
import com.nice.surveaweb.adapters.DtoAdapter;
import com.nice.surveaweb.dto.ErrorDto;
import com.nice.surveaweb.dto.TemplateRequest;
import com.nice.surveaweb.dto.TemplateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nice.surveaweb.adapters.DtoAdapter.toTemplateDto;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateUseCase templateUseCase;

    public TemplateController(TemplateUseCase templateUseCase) {
        this.templateUseCase = templateUseCase;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TemplateRequest templateRequest) {
        try {
            Template template = templateUseCase.createEmptyTemplate(templateRequest.getName());
            TemplateResponse templateResponse = new TemplateResponse(toTemplateDto(template));
            return new ResponseEntity(templateResponse, HttpStatus.CREATED);

        } catch (TemplateAlreadyExistsException exception) {
            return new ResponseEntity(new ErrorDto("TemplateAlreadyExists"), HttpStatus.CONFLICT);
        } catch (InvalidTemplateNameException exception) {
            return new ResponseEntity(new ErrorDto("WrongTemplateName"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getByName(@PathVariable(value = "name") String templateName) {
        Template template = templateUseCase.getByName(templateName).get();
        TemplateResponse templateResponse = new TemplateResponse(toTemplateDto(template));
        return ResponseEntity.ok(templateResponse);
    }

    @PutMapping("/{name}")
    public ResponseEntity update(@PathVariable(value = "name") String templateName, @RequestBody TemplateRequest templateRequest) {
        try {
            Template template = DtoAdapter.toTemplate(templateRequest.getTemplateDto());
            Template newTemplate = templateUseCase.update(template);
            return ResponseEntity.ok(newTemplate);

        } catch (WrongRangeQuestionException exception) {
            return new ResponseEntity(new ErrorDto("WrongRangeQuestion"), HttpStatus.BAD_REQUEST);
        }
    }
}
