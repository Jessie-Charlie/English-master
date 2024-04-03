package com.Englilsh.controller.student;


import com.Englilsh.base.BaseApiController;
import com.Englilsh.base.RestResponse;
import com.Englilsh.domain.Subject;
import com.Englilsh.domain.enums.SubjectLevelEnum;
import com.Englilsh.service.ExamPaperService;
import com.Englilsh.service.SubjectService;
import com.Englilsh.viewmodel.student.education.LevelVM;
import com.Englilsh.viewmodel.student.education.SubjectEditRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController("StudentEducationController")
@RequestMapping(value = "/api/student/education")
public class EducationController extends BaseApiController {


    @Resource
    private ExamPaperService examPaperService;

    private final SubjectService subjectService;

    @Autowired
    public EducationController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

//    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
//    public RestResponse<List<SubjectVM>> list() {
//        List<Subject> subjects = subjectService.allSubject();
//        List<SubjectVM> subjectVMS = subjects.stream().map(d -> {
//            SubjectVM subjectVM = modelMapper.map(d, SubjectVM.class);
//            subjectVM.setId(String.valueOf(d.getId()));
//            return subjectVM;
//        }).collect(Collectors.toList());
//        return RestResponse.ok(subjectVMS);
//    }

    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
    public RestResponse<List<LevelVM>> list() {
        List<Integer> allLevel = examPaperService.getAllLevel();
        List<LevelVM> levelVMS = new ArrayList<>();
        allLevel.forEach(level -> {
            LevelVM levelVM = new LevelVM();
            levelVM.setId(level);
            if(level == 1) levelVM.setName(SubjectLevelEnum.ONE.name());
            else if (level == 2) levelVM.setName(SubjectLevelEnum.TWO.name());
            else if (level == 4) levelVM.setName(SubjectLevelEnum.FOUR.name());
            else if (level == 6) levelVM.setName(SubjectLevelEnum.SIX.name());
            levelVMS.add(levelVM);
        });
        return RestResponse.ok(levelVMS);
    }

    @RequestMapping(value = "/subject/select/{id}", method = RequestMethod.POST)
    public RestResponse<SubjectEditRequestVM> select(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        SubjectEditRequestVM vm = modelMapper.map(subject, SubjectEditRequestVM.class);
        return RestResponse.ok(vm);
    }

}
