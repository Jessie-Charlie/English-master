package com.Englilsh.viewmodel.admin.question;

import lombok.Data;

import java.util.List;

@Data
public class QuestionEditResponseVM {
    private String name;
    private List<QuestionEditRequestVM> items;
}
