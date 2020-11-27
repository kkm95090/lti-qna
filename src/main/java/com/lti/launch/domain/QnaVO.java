package com.lti.launch.domain;

import lombok.Data;

import java.util.Date;

@Data
public class QnaVO {
    private int qnaNo;
    private int qnaAccountId;
    private int qnaCourseId;
    private String qnaTitle;
    private String qnaContents;
    private int qnaUserId;
    private String qnaSecret;
    private int qnaAttachId;
    private Date qnaInsertDate;
    private Date qnaUpdateDate;
    private String qnaDeleteYN;


}
