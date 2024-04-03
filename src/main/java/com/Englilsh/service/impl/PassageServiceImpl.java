package com.Englilsh.service.impl;

import com.Englilsh.domain.Passage;
import com.Englilsh.domain.TextContent;
import com.Englilsh.domain.passage.PassageObject;
import com.Englilsh.domain.question.QuestionObject;
import com.Englilsh.repository.PassageMapper;
import com.Englilsh.service.PassageService;
import com.Englilsh.service.QuestionService;
import com.Englilsh.service.TextContentService;
import com.Englilsh.utility.JsonUtil;
import com.Englilsh.utility.ModelMapperSingle;
import com.Englilsh.viewmodel.admin.passage.PassageEditRequestVM;
import com.Englilsh.viewmodel.admin.passage.PassagePageRequestVM;
import com.Englilsh.viewmodel.admin.passage.PassagePageResponseVM;
import com.Englilsh.viewmodel.admin.question.QuestionEditRequestVM;
import com.Englilsh.viewmodel.admin.question.QuestionEditResponseVM;
import com.Englilsh.viewmodel.admin.question.QuestionResponseVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PassageServiceImpl extends BaseServiceImpl<Passage> implements PassageService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    private final PassageMapper passageMapper;
    private final TextContentService textContentService;
    private final QuestionService questionService;

    @Autowired
    public PassageServiceImpl(PassageMapper passageMapper, TextContentService textContentService, QuestionService questionService) {
        super(passageMapper);
        this.passageMapper = passageMapper;
        this.textContentService = textContentService;
        this.questionService = questionService;
    }
    @Override
    public PageInfo<Passage> page(PassagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id").doSelectPageInfo(() ->
                passageMapper.page(requestVM)
        );
    }

    @Override
    @Transactional
    public Passage insertPassage(PassageEditRequestVM model, Integer userId) {
        Date now = new Date();

        // 短文内容插入
        TextContent infoTextContent = new TextContent();
        infoTextContent.setCreateTime(now);
        PassageObject passageObject = new PassageObject();
        passageObject.setContent(model.getContent());
        infoTextContent.setContent(JsonUtil.toJsonStr(passageObject));
        textContentService.insertByFilter(infoTextContent);


        Passage passage = new Passage();
        passage.setSubjectId(model.getSubjectId());
        passage.setSubjectLevel(model.getSubjectLevel());
        passage.setInfoTextContentId(infoTextContent.getId());
        passage.setSuggestTime(model.getSuggestTime());
        passage.setQuestionIds(model.getQuestionIds().toString().replaceAll("^\\[|\\]$", ""));
        passage.setCreateTime(now);
        passage.setCreateUser(userId);
        passage.setDeleted(false);

        passageMapper.insertSelective(passage);
        return passage;
    }

    @Override
    public Passage updatePassage(PassageEditRequestVM model) {
        Passage passage = passageMapper.selectByPrimaryKey(model.getId());
        passage.setSubjectLevel(model.getSubjectLevel());
        passage.setSubjectId(model.getSubjectId());
        passage.setSuggestTime(model.getSuggestTime());
        passageMapper.updateByPrimaryKeySelective(passage);

        // 短文内容更新
        TextContent infoTextContent = textContentService.selectById(passage.getInfoTextContentId());
        infoTextContent.setContent(model.getContent());
        textContentService.updateByIdFilter(infoTextContent);
        return passage;
    }

    @Override
    public PassagePageResponseVM getPassageById(Integer id) {
        Passage passage = passageMapper.selectByPrimaryKey(id);
        return getPassageEditReqById(passage);
    }
    @Override
    public PassagePageResponseVM getPassageEditReqById(Passage passage) {
        // 短文映射
        TextContent passageInfoTextContent = textContentService.selectById(passage.getInfoTextContentId());
        PassageObject passageObject = JsonUtil.toJsonObject(passageInfoTextContent.getContent(), PassageObject.class);
        PassagePageResponseVM passagePageResponseVM = modelMapper.map(passage, PassagePageResponseVM.class);
        passagePageResponseVM.setContent(passageObject.getContent());

        // 题目映射
        passagePageResponseVM.setQuestionIds(passage.getQuestionIds());
        List<QuestionEditResponseVM> questionItems = new ArrayList<>();
        String[] questionIds = passage.getQuestionIds().split(",");
        for(int i = 0;i < questionIds.length;i++)
        {
            QuestionEditResponseVM item = new QuestionEditResponseVM();
            List<QuestionEditRequestVM> items = new ArrayList<>();
            QuestionEditRequestVM question = questionService.getQuestionEditRequestVM(Integer.parseInt(questionIds[i].trim()));
            items.add(question);
            item.setItems(items);
            item.setName(question.getTitle());
            questionItems.add(item);
        }
        passagePageResponseVM.setQuestionItems(questionItems);
        return passagePageResponseVM;
    }
}
