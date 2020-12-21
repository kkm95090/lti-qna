package com.lti.launch.service;


import com.lti.launch.db.mybatis.dto.*;
import com.lti.launch.model.define.ContextType;
import com.lti.launch.model.request.ReqQna;
import com.lti.launch.model.request.ReqQnaReply;
import com.lti.launch.model.view.CourseModules;
import com.lti.launch.model.view.Module;
import com.lti.launch.db.mybatis.mapper.postgresql.PostgresSqlSampleMapper;
import com.lti.launch.model.view.Qna;
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


    public Qna QnaSelectList(Integer page, Integer size) {

        Integer qnalistCnt = postgresSqlSampleMapper.qnaListAllCnt();
        List<QnaDTO>  qnalist = postgresSqlSampleMapper.qnaListAll(size.intValue()
                , size.intValue() * (page.intValue()-1));
        Qna qnaListAll = new Qna();
        for(QnaDTO qna : qnalist){
            List<QnaReplyDTO> replys = new ArrayList<>();
            replys = postgresSqlSampleMapper.qnaReplyListAll(qna.getQna_no());

            qna.setQnaReplyDTOList(replys);
        }

        qnaListAll.setCnt(qnalistCnt);
        qnaListAll.setQna(qnalist);
            return qnaListAll;
    }

    public boolean insertQna(ReqQna req){
        boolean retVal = true;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        String secret = null;
        if(req.getSecret().equals("true")){
            secret = "Y";
        }else{
            secret = "N";
        }

//        File file = new File(req.getAttachFile());
//
//        if(file.exists()){
//            System.out.println("file find");
//        }else{
//            System.out.println("file not found");
//        }

        QnaDTO dto = QnaDTO.builder()
                .qna_course_id(req.getCourseId())
                .qna_user_id(req.getUserId())
                .qna_title(req.getQnaTitle())
                .qna_contents(req.getQnaContents())
                .qna_secret(secret)
                .qna_insert_date(ts)
                .qna_update_date(ts)
                .build();
        Long qna_no = postgresSqlSampleMapper.insertQna(dto);
        if(qna_no.longValue() <= 0 )retVal = false;

        return retVal;
    }

    public Object deleteQna(ReqQna req) {
        boolean retVal = true;


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        QnaDTO dto = QnaDTO.builder()
                .qna_no(req.getQna_no())
                .qna_update_date(ts)
                .build();

        postgresSqlSampleMapper.deleteqna(dto);


        return retVal;
    }

    public QnaDTO editQna(Integer qna_no) {

        QnaDTO resResult = postgresSqlSampleMapper.editqna(qna_no);
        return resResult;
    }

    public boolean updateQna(ReqQna req) {
        boolean retVal = true;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        String secret = null;
        if(req.getSecret().equals("true")){
            secret = "Y";
        }else{
            secret = "N";
        }

//        File file = new File(req.getAttachFile());
//
//        if(file.exists()){
//            System.out.println("file find");
//        }else{
//            System.out.println("file not found");
//        }

        QnaDTO dto = QnaDTO.builder()
                .qna_no(req.getQna_no())
                .qna_course_id(req.getCourseId())
                .qna_user_id(req.getUserId())
                .qna_title(req.getQnaTitle())
                .qna_contents(req.getQnaContents())
                .qna_secret(secret)
                .qna_update_date(ts)
                .build();
        postgresSqlSampleMapper.updateQna(dto);

        return retVal;
    }

    public boolean insertQnaReply(ReqQnaReply req) {
        boolean retVal = true;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);
        QnaReplyDTO dto = QnaReplyDTO.builder()
                .qna_no(req.getQnaNo())
                .qna_reply_contents(req.getQnaReplyContents())
                .qna_reply_insert_date(ts)
                .qna_reply_update_date(ts)
                .qna_reply_user_id(req.getReplyUserId())
                .qna_reply_user_role(req.getReplyUserRole())
                .build();
        System.out.println("replyservicedto: "+dto);
        Long qna_reply_no = postgresSqlSampleMapper.insertQnaReply(dto);
        if(qna_reply_no.longValue() <= 0 )retVal = false;
        return retVal;
    }

//    public Qna QnaReplySelect(Integer qna_no) {
//        //List<QnaReplyDTO>  qnareplylist = postgresSqlSampleMapper.qnaReplyListAll(qna_no);
//        Qna qnaReplyListAll = new Qna();
//        qnaReplyListAll.setQnaReply(postgresSqlSampleMapper.qnaReplyListAll(qna_no));
//
//        return qnaReplyListAll;
//    }
}
