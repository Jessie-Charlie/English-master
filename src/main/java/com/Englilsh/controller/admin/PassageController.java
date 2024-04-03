package com.Englilsh.controller.admin;

import com.Englilsh.base.BaseApiController;
import com.Englilsh.base.RestResponse;
import com.Englilsh.base.SystemCode;
import com.Englilsh.domain.Passage;
import com.Englilsh.domain.TextContent;
import com.Englilsh.domain.passage.PassageObject;
import com.Englilsh.domain.question.QuestionObject;
import com.Englilsh.service.PassageService;
import com.Englilsh.service.TextContentService;
import com.Englilsh.utility.*;
import com.Englilsh.viewmodel.admin.passage.PassageEditRequestVM;
import com.Englilsh.viewmodel.admin.passage.PassagePageRequestVM;
import com.Englilsh.viewmodel.admin.passage.PassagePageResponseVM;
import com.Englilsh.viewmodel.admin.question.QuestionEditRequestVM;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController("AdminPassageController")
@RequestMapping(value = "/api/admin/passage")
public class PassageController extends BaseApiController {
    @Resource
    private PassageService passageService;

    @Resource
    private TextContentService textContentService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<PassagePageResponseVM>> pageList(@RequestBody PassagePageRequestVM model) {
        PageInfo<Passage> pageInfo = passageService.page(model);
        PageInfo<PassagePageResponseVM> page = PageInfoHelper.copyMap(pageInfo, q -> {
            PassagePageResponseVM vm = modelMapper.map(q, PassagePageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(q.getCreateTime()));
            TextContent textContent = textContentService.selectById(q.getInfoTextContentId());
            PassageObject passageObject = JsonUtil.toJsonObject(textContent.getContent(), PassageObject.class);
            String clearHtml = HtmlUtil.clear(passageObject.getContent());
            vm.setContent(clearHtml);
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid PassageEditRequestVM model) {

        if (null == model.getId()) {
            passageService.insertPassage(model, getCurrentUser().getId());
        } else {
            passageService.updatePassage(model);
        }

        return RestResponse.ok();
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
        public RestResponse<PassagePageResponseVM> select(@PathVariable Integer id) {
        PassagePageResponseVM newVM = passageService.getPassageById(id);
        return RestResponse.ok(newVM);
    }
}
