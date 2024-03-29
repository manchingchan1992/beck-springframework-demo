package com.pccw.springframework.mvc;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.convertor.OfficeUserConvertor;
import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.dto.OfficeUserEnquireDTO;
import com.pccw.springframework.dto.OfficeUserPagedCriteria;
import com.pccw.springframework.service.OfficeUserManagementService;
import com.pccw.springframework.utility.StringEncodeUtility;
import com.pccw.springframework.validator.OfficeUserValidator;

@Controller
@SessionAttributes({"userEnquireDto" , 
	                "availableRoles" , 
	                "officeUserDto"})
public class UserManagementController extends BaseUserManagementController{
	
	@Autowired
	private OfficeUserManagementService officeUsrMgmtService;
	
	@Autowired
	private OfficeUserValidator officeUserValidator; 
	
	@RequestMapping(value="/authentication/usrMgmt/initUsrMgmt.do")
	public ModelAndView initUserManagement(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountManagement");
		List<OfficeRoleDTO> roles = officeUsrMgmtService.getAvailableRoles();
		mv.addObject("userEnquireDto", new OfficeUserEnquireDTO());
		mv.addObject("availableRoles", roles);
		return mv;
	}
	
	@RequestMapping(value="/authentication/usrMgmt/searchOfficeUsers.do")
	public ModelAndView searchUsers(HttpServletRequest request , @ModelAttribute("userEnquireDto")OfficeUserEnquireDTO userEnquireDto){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountManagement");
		handleSearch(request , userEnquireDto , mv);
		return mv;
	}

	private void handleSearch(HttpServletRequest request,
			final OfficeUserEnquireDTO userEnquireDto , ModelAndView mv) {
		TableModel model = new TableModel("usr_table", request);
		model.autoFilterAndSort(false);
		
		model.setItems(new PageItems() {
			
			public int getTotalRows(Limit arg0) {
				return officeUsrMgmtService.getUsersCountByCriteria(OfficeUserConvertor.toPagedCriteria(userEnquireDto));
			}
			
			public Collection<?> getItems(Limit limit) {
				OfficeUserPagedCriteria pagedCriteria = OfficeUserConvertor.toPagedCriteria(userEnquireDto);
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(rowEnd);
				
				List<OfficeUserDTO> users = officeUsrMgmtService.searchUsersByCriteria(pagedCriteria);
				return users;
			}
		});
		
		model.setTable(getTable(request ,userEnquireDto.getJmesaDto() , false));
		
		mv.addObject("html", model.render());
	}

	@RequestMapping(value="/authentication/usrMgmt/preCreateUserAccount.do")
	public ModelAndView preCreateUserAccount(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountCreate");
		mv.addObject("officeUserDto", new OfficeUserDTO());
		return mv;
	}
	
	@RequestMapping(value="/authentication/usrMgmt/createUserAccount.do")
	public ModelAndView createUserAccount(HttpServletRequest request, @ModelAttribute("officeUserDto")OfficeUserDTO officeUserDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("redirect:/authentication/usrMgmt/initUsrMgmt.do");
		
		officeUserValidator.validate(officeUserDto, errors, false);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("authentication/usrMgmt/accountCreate");
			mv.addObject("officeUserDto", officeUserDto);
			return mv;
		}
		
		
		return mv;
	}
	
	@RequestMapping(value="/authentication/usrMgmt/usrMaintenance.do")
	public ModelAndView userAccountMaintenance(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountMaintenance");
		String usrRecId = StringEncodeUtility.decode(request.getParameter("id") , CommonConstant.STRING_ENCODE_BY_DEFAULT);
		
		if(StringUtils.isEmpty(usrRecId)){
			mv = new ModelAndView("authentication/usrMgmt/accountManagement");
			return mv;
		}
		
		OfficeUserDTO officeUserDto = officeUsrMgmtService.getUserByUserRecId(usrRecId);
		mv.addObject("officeUserDto", officeUserDto);
		return mv;
	}
	
	@RequestMapping(value="/authentication/usrMgmt/updateOfficeUserDetail.do")
	public ModelAndView updateUser(HttpServletRequest request ,@ModelAttribute("officeUserDto")OfficeUserDTO officeUserDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountConfirmation");
		
		officeUserValidator.validate(officeUserDto, errors, true);
		if(errors.hasErrors()){
			mv = new ModelAndView("authentication/usrMgmt/accountMaintenance");
			mv.addObject("officeUserDto", officeUserDto);
			return mv;
		}
		
		officeUsrMgmtService.updateUser(officeUserDto);
		mv.addObject("officeUserDto", officeUserDto);
		return mv;
	}
	
	@RequestMapping(value="/authentication/usrMgmt/initResetPassword.do")
	public ModelAndView initResetPassword(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountPasswordReset");
		OfficeUserDTO officeUserDto = new OfficeUserDTO();
		mv.addObject("officeUserDto", officeUserDto);
		return mv;
	}
	
	@RequestMapping(value="/authentication/usrMgmt/initUsrRoleAsgn.do")
	public ModelAndView initUsrRoleAsgn(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountRoleAssign");
		String userRecId = StringEncodeUtility.decode(request.getParameter("id"), CommonConstant.STRING_ENCODE_BY_DEFAULT);
		OfficeUserDTO officeUserDto = officeUsrMgmtService.getUserByUserRecId(userRecId);
		mv.addObject("officeUserDto", officeUserDto);
		return mv;
	}
}
