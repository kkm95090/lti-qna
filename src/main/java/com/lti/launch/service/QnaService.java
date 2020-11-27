package com.lti.launch.service;

import com.lti.launch.domain.QnaVO;
import com.lti.launch.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {

    @Autowired
    public QnaMapper mapper ;

    public List<QnaVO> selectQnaList() throws Exception {
        return mapper.selectQnaList();
    }
}
