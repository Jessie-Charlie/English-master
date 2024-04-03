package com.Englilsh.service;

import com.Englilsh.domain.ExamPaper;
import com.Englilsh.domain.User;
import com.Englilsh.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.Englilsh.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.Englilsh.viewmodel.student.dashboard.PaperFilter;
import com.Englilsh.viewmodel.student.dashboard.PaperInfo;
import com.Englilsh.viewmodel.student.exam.ExamPaperPageVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExamPaperService extends BaseService<ExamPaper> {

    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> studentPage(ExamPaperPageVM requestVM);

    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM, User user);

    ExamPaperEditRequestVM examPaperToVM(Integer id);

    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    Integer selectAllCount();

    List<Integer> selectMothCount();

    List<Integer> getAllLevel();

}
