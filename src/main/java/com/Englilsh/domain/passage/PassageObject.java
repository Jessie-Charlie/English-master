package com.Englilsh.domain.passage;

import com.Englilsh.domain.question.QuestionItemObject;
import lombok.Data;

import java.util.List;

@Data
public class PassageObject {
    private String content;

    private List<QuestionItemObject> questionItemObjects;
}
