package com.Englilsh.service;

import com.Englilsh.domain.TaskExam;
import com.Englilsh.domain.User;
import com.Englilsh.viewmodel.admin.task.TaskPageRequestVM;
import com.Englilsh.viewmodel.admin.task.TaskRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskExamService extends BaseService<TaskExam> {

    PageInfo<TaskExam> page(TaskPageRequestVM requestVM);

    void edit(TaskRequestVM model, User user);

    TaskRequestVM taskExamToVM(Integer id);

    List<TaskExam> getByGradeLevel(Integer gradeLevel);
}
