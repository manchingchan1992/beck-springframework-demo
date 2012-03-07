package com.pccw.springframework.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.dto.EmailMessageDTO;

@Controller
public class MessageManagementController extends BaseController{
	
	@RequestMapping(value="/message/initEmail.do")
	public ModelAndView initNewEmail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("message/createEmail");
		EmailMessageDTO dto = new EmailMessageDTO();
		mv.addObject("messageDto", dto);
		return mv;
	}
}
