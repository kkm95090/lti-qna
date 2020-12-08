package com.lti.launch.service;


import com.lti.launch.db.mybatis.dto.*;
import com.lti.launch.model.define.ContextType;
import com.lti.launch.model.view.CourseModules;
import com.lti.launch.model.view.Module;
import com.lti.launch.db.mybatis.mapper.postgresql.PostgresSqlSampleMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QnaService {

    private static final Logger LOG = Logger.getLogger(QnaService.class);

    @Autowired
    private PostgresSqlSampleMapper postgresSqlSampleMapper;


    public void postgresSqlTest(){
        List<Map<String, String>> results = postgresSqlSampleMapper.findAll();

        for( Map<String, String> data : results){
            LOG.info( "name:" + data.get("name") );
        }
    }


    public CourseModules selectModule(long id){

        CourseModules retVal = new CourseModules();

        CourseDTO courseDTO = postgresSqlSampleMapper.findCourseByID(id);
        LOG.info( "-Course id:" + courseDTO.getId()
               + " name:" + courseDTO.getName()
               + " ContextType:" + ContextType.values()[0].getValue());

        retVal.setCourse(courseDTO);

       List<ContextModuleDTO> contextModuleDTOList = postgresSqlSampleMapper.findContextModulesByContextIDAndContextType(id, ContextType.values()[0].getValue());

       for(ContextModuleDTO data : contextModuleDTOList) {
           LOG.info("--Module id:" + data.getId()
                    + " name:" + data.getName());

           List<ContentTagDTO> contentTagDTOList = postgresSqlSampleMapper.findContextTagByContextModuleID(data.getId());

           for( ContentTagDTO subData : contentTagDTOList) {
               LOG.info("---Content id:" + subData.getId()
               + " title:" + subData.getTitle());
           }

           if (retVal.getModules() != null) {
               retVal.getModules().add(Module.builder()
                       .contextModule(data)
                       .contentTags(contentTagDTOList)
                       .build());
           } else {
               retVal.setModules(new ArrayList<Module>());
           }

       }

       return retVal;
    }


    public List<QnaDTO> QnaSelectList(PagingDTO paging) {
        LOG.info("Service"+paging);
        List<QnaDTO>  qnalist = postgresSqlSampleMapper.findQnaAll(paging);
            return qnalist;
    };

    public int qnaTotalCount() {
       return postgresSqlSampleMapper.qnaTotalCount();
    };


}
