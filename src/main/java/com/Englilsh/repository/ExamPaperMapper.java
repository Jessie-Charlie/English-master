package com.Englilsh.repository;

import com.Englilsh.domain.ExamPaper;
import com.Englilsh.domain.other.KeyValue;
import com.Englilsh.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.Englilsh.viewmodel.student.dashboard.PaperFilter;
import com.Englilsh.viewmodel.student.dashboard.PaperInfo;
import com.Englilsh.viewmodel.student.exam.ExamPaperPageVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExamPaperMapper extends BaseMapper<ExamPaper> {

    List<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    List<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    List<ExamPaper> studentPage(ExamPaperPageVM requestVM);

    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    Integer selectAllCount();

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int updateTaskPaper(@Param("taskId") Integer taskId,@Param("paperIds") List<Integer> paperIds);

    int clearTaskPaper(@Param("paperIds") List<Integer> paperIds);

    List<Integer> getAllLevel();
}
