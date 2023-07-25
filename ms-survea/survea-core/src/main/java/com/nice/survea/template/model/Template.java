package com.nice.survea.template.model;


import com.nice.survea.questions.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Template {
    String name;
    List<Question> questions;

    public Template(String name) {
        this(name, new ArrayList<>());
    }
}
