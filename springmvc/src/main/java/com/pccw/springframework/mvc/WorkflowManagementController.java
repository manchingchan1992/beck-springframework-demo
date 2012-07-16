package com.pccw.springframework.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.dto.WorkflowEnquireDTO;

@Controller
public class WorkflowManagementController extends BaseController{
	
	@RequestMapping(value="/workflow/initWorkflowSearch.do")
	public ModelAndView initWorkflowSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("workflow/workflowSearch");
		mv.addObject("workflowEnquireDto", new WorkflowEnquireDTO());
		return mv;
	}
}
