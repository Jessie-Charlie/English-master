package com.Englilsh.viewmodel.student.exam;

import com.Englilsh.viewmodel.admin.exam.ExamPaperEditRequestVM;

public class ExamPaperReadVM {
    private ExamPaperEditRequestVM paper;
    private ExamPaperSubmitVM answer;

    public ExamPaperEditRequestVM getPaper() {
        return paper;
    }

    public void setPaper(ExamPaperEditRequestVM paper) {
        this.paper = paper;
    }

    public ExamPaperSubmitVM getAnswer() {
        return answer;
    }

    public void setAnswer(ExamPaperSubmitVM answer) {
        this.answer = answer;
    }
}
