package com.lti.launch.db.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaReplyDTO {
    private int qna_reply_no;
    private int qna_no;
    private String qna_reply_contents;
    private int qna_reply_user_id;
    private String qna_reply_user_role;
    private Date qna_reply_insert_date;
    private Date qna_reply_update_date;
    private String qna_delete_YN;
}
