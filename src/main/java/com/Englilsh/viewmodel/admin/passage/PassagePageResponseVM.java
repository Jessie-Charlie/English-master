package com.Englilsh.viewmodel.admin.passage;

import com.Englilsh.viewmodel.BaseVM;
import com.Englilsh.viewmodel.admin.question.QuestionEditRequestVM;
import com.Englilsh.viewmodel.admin.question.QuestionEditResponseVM;
import com.Englilsh.viewmodel.admin.question.QuestionResponseVM;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PassagePageResponseVM extends BaseVM {

    private Integer id;
    private Integer subjectLevel;
    private Integer subjectId;
    private Integer InfoTextContentId;
    private String suggestTime;
    private String createTime;
    private String content;
    private String questionIds;
    private List<QuestionEditResponseVM> questionItems;

}
