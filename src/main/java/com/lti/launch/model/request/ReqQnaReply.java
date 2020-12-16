package com.lti.launch.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqQnaReply {
    private int qnaNo;
    private int qnaReplyNo;
    private String qnaReplyContents;
    private int replyUserId;
    private String replyUserRole;


}
