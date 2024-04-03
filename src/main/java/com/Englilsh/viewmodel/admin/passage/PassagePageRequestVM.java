package com.Englilsh.viewmodel.admin.passage;

import com.Englilsh.base.BasePage;
import lombok.Data;

import java.util.List;


@Data
public class PassagePageRequestVM extends BasePage {
    private Integer id;

    private List<Integer> questionIds;

    private Integer subjectLevel;

    private Integer subjectId;

}
