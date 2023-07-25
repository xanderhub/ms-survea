package com.nice.survea.survey.model;

import com.nice.survea.questions.model.Question;
import com.nice.survea.reply.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Survey {
    private String name;
    private String templateName;
    private List<Question> questions;
}
