package com.pccw.springframework.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends BaseController{
	
	@RequestMapping(value="/index.do")
	public ModelAndView index(HttpServletRequest request){
		logger.info(">>>>> Index.do Start ....");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login.do")
	public ModelAndView login(HttpServletRequest request){
		logger.info(">>>> return login page");
		return new ModelAndView("login");
	}
}
