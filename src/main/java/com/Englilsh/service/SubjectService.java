package com.Englilsh.service;

import com.Englilsh.domain.Subject;
import com.Englilsh.viewmodel.admin.education.SubjectPageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SubjectService extends BaseService<Subject> {

    List<Subject> getSubjectByLevel(Integer level);

    List<Subject> allSubject();

    PageInfo<Subject> page(SubjectPageRequestVM requestVM);
}
