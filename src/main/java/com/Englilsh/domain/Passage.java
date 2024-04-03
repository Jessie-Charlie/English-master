package com.Englilsh.domain;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Passage {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 短文所属试卷类型（四六级等）
     */
    private Integer subjectLevel;
    /**
     * 短文所属题目类别（use of English等）
     */
    private Integer subjectId;

    private Integer infoTextContentId;

    private String questionIds;

    /**
     * 建议时长
     */
    private String suggestTime;

    private Integer createUser;

    private Date createTime;

    private Boolean deleted;
}
