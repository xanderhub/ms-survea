package com.nice.survea.reply.usecase;

import com.nice.survea.answers.model.Answer;
import com.nice.survea.questions.model.Question;
import com.nice.survea.questions.model.RangeQuestion;
import com.nice.survea.questions.model.TextQuestion;
import com.nice.survea.reply.exceptions.InvalidAnswerException;
import com.nice.survea.reply.exceptions.SurveyNotExistException;
import com.nice.survea.reply.gateway.ReplyGateway;
import com.nice.survea.reply.gateway.ReplyGatewayFake;
import com.nice.survea.reply.model.Reply;
import com.nice.survea.survey.gateway.SurveyGatewayFake;
import com.nice.survea.survey.model.Survey;
import com.nice.survea.survey.usecase.SurveyInteractor;
import com.nice.survea.survey.usecase.SurveyUseCase;
import com.nice.survea.template.gateway.TemplateGateway;
import com.nice.survea.template.gateway.TemplateGatewayFake;
import com.nice.survea.template.model.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReplyInteractorTests {

    private ReplyInteractor replyInteractor;
    private ReplyGateway replyGateway;
    private TemplateGateway templateGateway;
    private SurveyUseCase surveyUseCase;

    @BeforeEach
    void setUp() {
        templateGateway = new TemplateGatewayFake();
        replyGateway = new ReplyGatewayFake();
        surveyUseCase = new SurveyInteractor(new SurveyGatewayFake(), templateGateway);
        replyInteractor = new ReplyInteractor(replyGateway, surveyUseCase);
    }

    @Test
    public void createShouldThrowExceptionWhenSurveyNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> replyInteractor.create(null));
    }

    @Test
    public void createShouldThrowExceptionWhenSurveyNameIsEmpty() {
        Reply reply = new Reply("");
        assertThrows(IllegalArgumentException.class, () -> replyInteractor.create(reply));
    }

    @Test
    public void getShouldThrowExceptionWhenSurveyNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> replyInteractor.getBySurveyName(null));
    }

    @Test
    public void getShouldThrowExceptionWhenSurveyNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> replyInteractor.getBySurveyName(""));
    }

    @Test
    public void createShouldThrowExceptionWhenSurveyDoesNotExists() {
        assertThrows(SurveyNotExistException.class, () -> replyInteractor.create(new Reply("nonExistingSurvey")));
    }

    @Test
    public void getShouldThrowExceptionWhenSurveyDoesNotExist() {
        assertThrows(SurveyNotExistException.class, () -> replyInteractor.getBySurveyName("nonExistingSurvey"));
    }

    @Test
    public void createShouldSucceedWhenAnswerIsNull() {
        Survey survey = createTestSurveyWithTestQuestions();
        List<Answer> answers = new ArrayList<Answer>() {{
            add(null);
            add(null);
        }};

        Reply reply = new Reply(survey.getName(), answers);

        replyInteractor.create(reply);
        assertNotNull(replyGateway.getBySurveyName(survey.getName()));
    }

    @Test
    public void createShouldSucceedWhenTextInAnswerIsNull() {
        Survey survey = createTestSurveyWithTestQuestions();
        List<Answer> answers = new ArrayList<Answer>() {{
            add(new Answer(null));
            add(new Answer(null));
        }};

        Reply reply = new Reply(survey.getName(), answers);

        replyInteractor.create(reply);
        assertNotNull(replyGateway.getBySurveyName(survey.getName()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Wrong", "0", "11", "#wrong", "-42"})
    public void createShouldThrowExceptionWhenAnswerToRangeQuestionIsInvalid(String answer) {
        Survey survey = createTestSurveyWithTestQuestions();
        List<Answer> answers = new ArrayList<Answer>() {{
            add(new Answer("OK"));
            add(new Answer(answer));
        }};

        Reply reply = new Reply(survey.getName(), answers);
        assertThrows(InvalidAnswerException.class, () -> replyInteractor.create(reply));
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ", "   "})
    public void createShouldThrowExceptionWhenAnswerToTextQuestionIsInvalid(String answer) {
        Survey survey = createTestSurveyWithTestQuestions();
        List<Answer> answers = new ArrayList<Answer>() {{
            add(new Answer(answer));
            add(new Answer("1"));
        }};

        Reply reply = new Reply(survey.getName(), answers);
        assertThrows(InvalidAnswerException.class, () -> replyInteractor.create(reply));
    }

    private Survey createTestSurveyWithTestQuestions() {
        Template template = templateGateway.createTemplate(new Template("TestTemplate"));

        template.getQuestions().addAll(new ArrayList<Question>() {{
            add(new TextQuestion("TextQuestion"));
            add(new RangeQuestion("RangeQuestion", 1, 10));
        }});

        return surveyUseCase.create("TestSurvey", "TestTemplate");
    }
}
