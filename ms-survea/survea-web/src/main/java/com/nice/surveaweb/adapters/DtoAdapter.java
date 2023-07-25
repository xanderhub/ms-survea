package com.nice.surveaweb.adapters;

import com.nice.survea.answers.model.Answer;
import com.nice.survea.questions.model.Question;
import com.nice.survea.questions.model.RangeQuestion;
import com.nice.survea.questions.model.TextQuestion;
import com.nice.survea.reply.model.Reply;
import com.nice.survea.survey.model.Survey;
import com.nice.survea.template.model.Template;
import com.nice.surveaweb.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DtoAdapter {

    public static TemplateDto toTemplateDto(Template template) {
        List<QuestionDto> questions = template
                .getQuestions()
                .stream()
                .map(DtoAdapter::toQuestionDto)
                .collect(Collectors.toList());
        return new TemplateDto(template.getName(), questions);
    }

    public static Template toTemplate(TemplateDto templateDto) {
        List<Question> questions = templateDto.getQuestions()
                .stream()
                .map(DtoAdapter::toQuestion)
                .collect(Collectors.toList());
        return new Template(templateDto.getName(), questions);
    }

    public static Question toQuestion(QuestionDto questionDto) {
        if (questionDto instanceof TextQuestionDto) {
            TextQuestionDto textQuestionDto = (TextQuestionDto) questionDto;
            return new TextQuestion(textQuestionDto.getQuestionText());
        }
        else if (questionDto instanceof RangeQuestionDto) {
            RangeQuestionDto rangeQuestionDto = (RangeQuestionDto) questionDto;
            return new RangeQuestion(rangeQuestionDto.getQuestion(), rangeQuestionDto.getMinValue(), rangeQuestionDto.getMaxValue());
        }
        return null;
    }

    public static QuestionDto toQuestionDto(Question question) {
        if (question instanceof TextQuestion) {
            TextQuestion textQuestion = (TextQuestion) question;
            return new TextQuestionDto(textQuestion.getText());
        }
        else if (question instanceof RangeQuestion) {
            RangeQuestion rangeQuestion = (RangeQuestion) question;
            return new RangeQuestionDto(rangeQuestion.getText(), rangeQuestion.getMin(), rangeQuestion.getMax());
        }
        return null;
    }


    public static Survey toSurvey(SurveyDto surveyDto) {
        List<Question> questions;
        if (surveyDto.getQuestions() == null) {
            questions = new ArrayList<>();
        } else {
            questions = surveyDto.getQuestions()
                    .stream()
                    .map(DtoAdapter::toQuestion)
                    .collect(Collectors.toList());
        }

        return new Survey(surveyDto.getName(), surveyDto.getTemplateName(), questions);
    }

    public static SurveyDto toSurveyDto(Survey survey) {

        List<QuestionDto> questions = null;

        if (survey.getQuestions() != null) {
            questions = survey.getQuestions()
                    .stream()
                    .map(DtoAdapter::toQuestionDto)
                    .collect(Collectors.toList());
        }

        return new SurveyDto(survey.getName(), survey.getTemplateName(), questions);
    }

    public static Reply toReply(ReplyDto replyDto) {
        List<Answer> answers;
        if (replyDto.getAnswers() == null) {
            answers = new ArrayList<>();
        } else {
            answers = replyDto.getAnswers()
                    .stream()
                    .map(DtoAdapter::toAnswer)
                    .collect(Collectors.toList());
        }

        return new Reply(replyDto.getSurveyName(), answers);
    }

    public static ReplyDto toReplyDto(Reply reply) {

        List<AnswerDto> answers = null;

        if (reply.getAnswers() != null) {
            answers = reply.getAnswers()
                    .stream()
                    .map(DtoAdapter::toAnswerDto)
                    .collect(Collectors.toList());
        }

        return new ReplyDto(reply.getSurveyName(), answers);
    }

    public static Answer toAnswer(AnswerDto answerDto) {
        return new Answer(answerDto.getText());
    }

    public static AnswerDto toAnswerDto(Answer answer) {
        return new AnswerDto(answer.getText());
    }
}
