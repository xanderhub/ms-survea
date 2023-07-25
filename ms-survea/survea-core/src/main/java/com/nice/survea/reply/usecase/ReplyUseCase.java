package com.nice.survea.reply.usecase;

import com.nice.survea.reply.model.Reply;

public interface ReplyUseCase {
    Reply create(Reply reply);
    Reply getBySurveyName(String surveyName);
}
