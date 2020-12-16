package com.lti.launch.model.view;

import com.lti.launch.db.mybatis.dto.QnaDTO;
import com.lti.launch.db.mybatis.dto.QnaReplyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qna {
    private List<QnaDTO> qna;
    private Integer cnt;
    private List<QnaReplyDTO> qnaReply;


}
