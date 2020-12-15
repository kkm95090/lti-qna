package com.lti.launch.controller;

import com.lti.launch.common.ResponseCode;
import com.lti.launch.db.mybatis.dto.QnaDTO;
import com.lti.launch.domain.BaseResult;
import com.lti.launch.domain.BaseResultFactory;
import com.lti.launch.model.request.ReqQna;
import com.lti.launch.model.view.Qna;
import com.lti.launch.service.QnaService;
import edu.ksu.lti.launch.exception.NoLtiSessionException;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class QnaController {

    private static final Logger LOG = Logger.getLogger(QnaController.class);

    @Autowired
    public QnaService qnaService;




    @GetMapping("qnaListAll")
    public BaseResult<Qna> qnaListAll( @ApiParam(value = "페이지(1-based)", example = "1", required = true)
                                                    @RequestParam(value = "page") Integer page,
                                                @ApiParam(value = "한 페이지에 노출될 개수", defaultValue = "5")
                                                    @RequestParam(value = "size", defaultValue = "5") Integer size) throws NoLtiSessionException {



        Qna modules = qnaService.QnaSelectList(page,size);
        System.out.println(modules);

        return BaseResultFactory.createSuccess(modules);
    }

    @PostMapping("qnaCreate")
    public BaseResult postQna(@RequestBody ReqQna req){

      if(qnaService.insertQna(req))  return BaseResultFactory.createSuccess();
      else return BaseResultFactory.createFail(ResponseCode.FAIL);
    }

    @PutMapping("qnaDelete")
    public BaseResult putQnaDelete(@RequestBody ReqQna req){
        System.out.println("controllerreq: "+req.getQnaNo());
        return BaseResultFactory.createSuccess(qnaService.deleteQna(req));
    }


}
