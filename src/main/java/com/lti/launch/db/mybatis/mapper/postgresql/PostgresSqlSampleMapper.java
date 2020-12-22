package com.lti.launch.db.mybatis.mapper.postgresql;

import com.lti.launch.db.mybatis.dto.*;
import com.lti.launch.model.request.ReqQnaReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@PostgresSqlConnMapper
public interface PostgresSqlSampleMapper {



    public List<Map<String, String>> findAll();

    public CourseDTO findCourseByID(@Param("id") long id);

    public List<ContextModuleDTO> findContextModulesByContextIDAndContextType(@Param("contextID") long contextID,
                                                                              @Param("contextType") String contextType);
    public List<ContentTagDTO> findContextTagByContextModuleID(@Param("contextModuleID") long contextModuleID);

    public List<QnaDTO> qnaListAll( @Param("size") int size,
                                    @Param("offset") long offset);

    Integer qnaListAllCnt();


    public Long insertQna(QnaDTO dto);

    public void deleteqna(QnaDTO dto);

    public QnaDTO editqna(@Param("qna_no") Integer qna_no);

    public Long updateQna(QnaDTO dto);

    public Long insertQnaReply(QnaReplyDTO dto);

    public List<QnaReplyDTO> qnaReplyListAll(@Param("qna_no") int qna_no);

    public void deleteReply(QnaReplyDTO dto);

    public QnaReplyDTO editqnaReply(@Param("qna_reply_no") Integer qna_reply_no);

    public void updateQnaReply(QnaReplyDTO dto);
}
