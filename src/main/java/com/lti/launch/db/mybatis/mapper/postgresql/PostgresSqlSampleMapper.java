package com.lti.launch.db.mybatis.mapper.postgresql;

import com.lti.launch.db.mybatis.dto.*;
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

    public List<QnaDTO> findQnaAll();

//    public int qnaTotalCount();

    public Long insertQna(QnaDTO dto);


}
