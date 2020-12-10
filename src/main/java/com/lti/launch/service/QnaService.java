package com.lti.launch.service;


import com.lti.launch.db.mybatis.dto.*;
import com.lti.launch.model.define.ContextType;
import com.lti.launch.model.request.ReqQna;
import com.lti.launch.model.view.CourseModules;
import com.lti.launch.model.view.Module;
import com.lti.launch.db.mybatis.mapper.postgresql.PostgresSqlSampleMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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


    public List<QnaDTO> QnaSelectList() {
        List<QnaDTO>  qnalist = postgresSqlSampleMapper.findQnaAll();
            return qnalist;
    };

//    public int qnaTotalCount() {
//       return postgresSqlSampleMapper.qnaTotalCount();
//    };


    public boolean insertQna(ReqQna req){
        boolean retVal = true;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);


        File file = new File(req.getAttachFile());

        if(file.exists()){
            System.out.println("file find");
        }else{
            System.out.println("file not found");
        }

        QnaDTO dto = QnaDTO.builder()
                .qna_course_id(req.getCourseId())
                .qna_user_id(req.getUserId())
                .qna_title(req.getQnaTitle())
                .qna_contents(req.getQnaContents())
                .qna_secret(req.getSecret())
                .qna_insert_date(ts)
                .qna_update_date(ts)
                .build();
        Long qnaId = postgresSqlSampleMapper.insertQna(dto);
        if(qnaId.longValue() <= 0 )retVal = false;

        return retVal;
    }
}
