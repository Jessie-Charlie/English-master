package com.Englilsh.service;

import com.Englilsh.domain.Passage;
import com.Englilsh.domain.Question;
import com.Englilsh.viewmodel.admin.passage.PassageEditRequestVM;
import com.Englilsh.viewmodel.admin.passage.PassagePageRequestVM;
import com.Englilsh.viewmodel.admin.passage.PassagePageResponseVM;
import com.Englilsh.viewmodel.admin.question.QuestionEditRequestVM;
import com.github.pagehelper.PageInfo;

public interface PassageService extends BaseService<Passage>{
    PageInfo<Passage> page(PassagePageRequestVM requestVM);
    Passage insertPassage(PassageEditRequestVM model, Integer userId);

    Passage updatePassage(PassageEditRequestVM model);

    PassagePageResponseVM getPassageById(Integer id);
    PassagePageResponseVM getPassageEditReqById(Passage passage);
}
