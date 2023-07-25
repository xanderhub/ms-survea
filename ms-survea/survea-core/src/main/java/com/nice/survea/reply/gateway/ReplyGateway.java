package com.nice.survea.reply.gateway;

import com.nice.survea.reply.model.Reply;

public interface ReplyGateway {
    Reply create(Reply reply);
    Reply getBySurveyName(String surveyName);
}
