package com.Englilsh.viewmodel.admin.passage;

import lombok.Data;

import java.util.List;

@Data
public class PassageEditRequestVM {

    private Integer id;
    private Integer subjectLevel;
    private Integer subjectId;
    private String content;
    private List<Integer> questionIds;
    private Integer InfoTextContentId;
    private String suggestTime;
}
