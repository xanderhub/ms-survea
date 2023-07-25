package com.nice.surveaweb;

import com.nice.survea.reply.model.Reply;
import com.nice.survea.template.model.Template;
import com.nice.surveaweb.dto.ErrorDto;
import com.nice.surveaweb.dto.ReplyDto;
import com.nice.surveaweb.dto.SurveyDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestContext {
    private Template template;
    private ReplyDto reply;
    private SurveyDto surveyDto;
    private ErrorDto errorDto;
}
