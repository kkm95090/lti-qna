package com.lti.launch.controller;

import com.lti.launch.db.mybatis.dto.PagingDTO;
import com.lti.launch.db.mybatis.dto.QnaDTO;
import com.lti.launch.model.view.CourseModules;
import com.lti.launch.service.QnaService;
import edu.ksu.lti.launch.controller.LtiLaunchController;
import edu.ksu.lti.launch.controller.OauthController;
import edu.ksu.lti.launch.model.LtiLaunchData;
import edu.ksu.lti.launch.model.LtiSession;
import edu.ksu.lti.launch.service.LtiSessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Scope("request")
public class LTILaunchExampleController extends LtiLaunchController {
    private static final Logger LOG = Logger.getLogger(LTILaunchExampleController.class);

    @Autowired
    public LtiSessionService ltiSessionService;

    @Autowired
    public QnaService qnaService;

    /**
     * We have our applications return the LTI configuration XML when you hit
     * the root of the context in a browser. It's an easy place to keep
     * the necessary XML and this method sets the host name/port to appropriate
     * values when running in dev/test by examining the incoming HTTP request.
     */
    @RequestMapping("/")
    public ModelAndView basePath(HttpServletRequest request) {
        LOG.info("Showing Activity Reporting configuration XML");
        String ltiLaunchUrl = OauthController.getApplicationBaseUrl(request, true) + "/launch";
        LOG.debug("LTI launch URL: " + ltiLaunchUrl);
        return new ModelAndView("ltiConfigure", "url", ltiLaunchUrl);
    }

	/*
	 * @RequestMapping("/helloWorld") public ModelAndView showButton() throws
	 * NoLtiSessionException { LtiSession ltiSession =
	 * ltiSessionService.getLtiSession(); if (ltiSession.getEid() == null ||
	 * ltiSession.getEid().isEmpty()) { throw new
	 * AccessDeniedException("You cannot access this content without a valid session"
	 * ); } return new ModelAndView("helloWorld", "username", ltiSession.getEid());
	 * }
	 */

    @RequestMapping("/helloWorld")
    public ModelAndView helloWorld(HttpServletRequest request
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
        LOG.info("Showing Activity Reporting configuration XML");
        LOG.info("nowPage"+nowPage);
        LOG.info("cntPerPage"+cntPerPage);
        String ltiLaunchUrl = OauthController.getApplicationBaseUrl(request, true) + "/launch";
//        LtiSession ltiSession = null;
//        if (ltiSessionService == null) {
//            ltiSession = new LtiSession();
//            ltiSession.setCanvasCourseId("1");
//        } else {
//            ltiSession = ltiSessionService.getLtiSession();
//            LOG.info("ltiSession"+ltiSession);
//        }
//
//        if (ltiSession.getEid() == null || ltiSession.getEid().isEmpty()) {
//            throw new AccessDeniedException("You cannot access this content without a valid session");
//        }
//
//        LtiLaunchData ltiLaunchData=ltiSession.getLtiLaunchData();

        //CourseModules courseModules = qnaService.selectModule(Long.parseLong(ltiSession.getCanvasCourseId()));

        CourseModules courseModules = qnaService.selectModule(1);



        ModelAndView view = new ModelAndView("qnaList");

//        view.addObject("paging", paging);
//        view.addObject("qna", qna);

        return  view;

    }

    @RequestMapping("/qnaList")
    public ModelAndView qnaList() throws Exception {
        ModelAndView view = new ModelAndView("qnaList");
        return view;
    }
    /*
     * After authenticating the LTI launch request, the user is forwarded to
     * this path. It is the initial page your user will see in their browser.
     */
    @Override
    protected String getInitialViewPath() {
        return "qnaList";
    }

    @Override
    protected String getApplicationName() {
        return "LTI Launch Example";
    }
}

