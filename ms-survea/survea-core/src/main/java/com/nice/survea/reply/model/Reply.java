package com.nice.survea.reply.model;


import com.nice.survea.answers.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private String surveyName;
    private List<Answer> answers;

    public Reply(String name) {
        this(name, new ArrayList<>());
    }
}
