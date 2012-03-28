package com.pccw.springframework.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.dto.OfficeUserDTO;

@Controller
public class UserManagementController extends BaseController{
	
	@RequestMapping(value="/authentication/usrMgmt/initUsrMgmt.do")
	public ModelAndView initUserManagement(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountManagement");
		mv.addObject("officeUserDto", new OfficeUserDTO());
		return mv;
	}
}
