package com.lti.launch.controller;

import com.lti.launch.common.ResponseCode;
import com.lti.launch.db.mybatis.dto.QnaDTO;
import com.lti.launch.domain.BaseResult;
import com.lti.launch.domain.BaseResultFactory;
import com.lti.launch.model.request.ReqQna;
import com.lti.launch.service.QnaService;
import com.sun.prism.impl.BaseResourceFactory;
import edu.ksu.lti.launch.model.LtiLaunchData;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class QnaController {

    private static final Logger LOG = Logger.getLogger(QnaController.class);

    @Autowired
    public QnaService qnaService;



    @GetMapping("qnaListAll")
    public BaseResult<List<QnaDTO>> getQnaList() {

        List<QnaDTO> modules = qnaService.QnaSelectList();

        return BaseResultFactory.createSuccess(modules);
    }

    @PostMapping("/qnaCreate")
    public BaseResult postQna(@RequestBody ReqQna req,
                              @ApiParam(value = "과목 코드", example = "7", required = true)
                              @RequestParam("courseId") long courseId,
                              @ApiParam(value = "사용자 코드", example = "1", required = true)
                                  @RequestParam("userId") long userId){

      if(qnaService.insertQna(req))  return BaseResultFactory.createSuccess();
      else return BaseResultFactory.createFail(ResponseCode.FAIL);
    }


}
