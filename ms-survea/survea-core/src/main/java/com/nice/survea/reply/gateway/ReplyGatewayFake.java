package com.nice.survea.reply.gateway;

import com.nice.survea.reply.model.Reply;

import java.util.HashMap;
import java.util.Map;

public class ReplyGatewayFake implements ReplyGateway {

    Map<String, Reply> replies = new HashMap<>();

    @Override
    public Reply create(Reply reply) {
        replies.put(reply.getSurveyName(), reply);
        return reply;
    }

    @Override
    public Reply getBySurveyName(String surveyName) {
        return replies.get(surveyName);
    }
}

