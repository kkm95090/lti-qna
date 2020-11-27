package com.lti.launch.mapper;

import com.lti.launch.domain.QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com/lti/launch/mapper/QnaMapper")
@Mapper
public interface QnaMapper {

    int selectQnaLsitCnt() throws Exception;

    List<QnaVO> selectQnaList() throws Exception;
}
