package com.lti.launch.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqQna {
    private int courseId;
    private int userId;
    private String qnaTitle;
    private String qnaContents;
    private String attachFile;
    private String secret;
    private int qna_no;
}
