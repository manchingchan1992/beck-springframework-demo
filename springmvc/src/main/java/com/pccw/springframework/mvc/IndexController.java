package com.pccw.springframework.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.utility.RedirectViewExt;

@Controller
public class IndexController extends BaseController{
	
	@RequestMapping(value="/index.do")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/message/initInbox.do", true));
		request.getSession(false).setAttribute("springframework_in_session", "springframework_in_session");
		return mv;
	}
	
	@RequestMapping(value="/login.do")
	public ModelAndView login(HttpServletRequest request){
		logger.debug(">>>>> return login page");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/logout.do")
	public ModelAndView logout(HttpServletRequest request){
		logger.debug(">>>>> logout");
		ModelAndView mv = new ModelAndView("login");
		String key = request.getParameter("key");
		if("session_t_o".equals(key)){
			mv.addObject("error_code_not_from_security", "OA-E-0001");
		}
		return mv;
	}
}
