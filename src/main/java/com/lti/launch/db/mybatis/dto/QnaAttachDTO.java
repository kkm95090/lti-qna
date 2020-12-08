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
public class QnaAttachDTO {
    private int qna_attach_no;
    private int qna_no;
    private String qna_attach_name;
    private String qna_attach_path;
    private String qna_attach_extender;
    private int qna_attach_size;
    private String qna_attach_del_YN;
    private Date qna_attach_insert_date;
    private Date qna_attach_update_date;
}
