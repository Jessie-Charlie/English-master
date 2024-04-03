package com.Englilsh.viewmodel.admin.question;

import com.Englilsh.base.BasePage;
import lombok.Data;


@Data
public class QuestionPageRequestVM extends BasePage {

    private Integer id;
    private Integer questionType;
    private String content;

}
