package com.nice.survea.reply.usecase;

import com.nice.survea.answers.model.Answer;
import com.nice.survea.questions.model.Question;
import com.nice.survea.questions.model.RangeQuestion;
import com.nice.survea.reply.exceptions.InvalidAnswerException;
import com.nice.survea.reply.exceptions.SurveyNotExistException;
import com.nice.survea.reply.gateway.ReplyGateway;
import com.nice.survea.reply.model.Reply;
import com.nice.survea.survey.model.Survey;
import com.nice.survea.survey.usecase.SurveyUseCase;

import java.util.List;
import java.util.Optional;

import static com.nice.survea.util.Validators.validateRangeAnswer;
import static com.nice.survea.util.Validators.validateTextAnswer;

public class ReplyInteractor implements ReplyUseCase {

    private final ReplyGateway replyGateway;
    private final SurveyUseCase surveyUseCase;

    public ReplyInteractor(ReplyGateway replyGateway, SurveyUseCase surveyUseCase) {
        this.replyGateway = replyGateway;
        this.surveyUseCase = surveyUseCase;
    }

    @Override
    public Reply create(Reply reply) {
        if (reply == null)
            throw new IllegalArgumentException();

        Survey survey = getSurvey(reply.getSurveyName());

        for(int i = 0; i < survey.getQuestions().size(); i++) {
           validateAnswer(survey.getQuestions().get(i), i, reply.getAnswers());
        }

        replyGateway.create(reply);
        return reply;
    }

    @Override
    public Reply getBySurveyName(String surveyName) {
        getSurvey(surveyName);
        return replyGateway.getBySurveyName(surveyName);
    }

    private Survey getSurvey(String surveyName) {
        return Optional.ofNullable(surveyUseCase.getByName(surveyName))
                .orElseThrow(SurveyNotExistException::new);
    }

    private void validateAnswer(Question question, Integer questionNumber, List<Answer> answers) {
        boolean validAnswer = true;
        if (questionNumber < answers.size() && answers.get(questionNumber) != null) {
            if (question.getType().equals("Text")) {
                Answer answer = answers.get(questionNumber);
                validAnswer = validateTextAnswer(answer.getText());
            } else {
                Answer answer = answers.get(questionNumber);
                validAnswer = validateRangeAnswer(answer.getText(), ((RangeQuestion)question).getMin(), ((RangeQuestion)question).getMax());
            }
        }

        if(!validAnswer) {
            throw new InvalidAnswerException();
        }
    }
}
