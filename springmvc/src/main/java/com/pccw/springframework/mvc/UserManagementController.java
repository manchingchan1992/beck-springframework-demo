package com.pccw.springframework.mvc;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.convertor.OfficeUserConvertor;
import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.dto.OfficeUserEnquireDTO;
import com.pccw.springframework.dto.OfficeUserPagedCriteria;
import com.pccw.springframework.service.OfficeUserManagementService;

@Controller
@SessionAttributes({"userEnquireDto" , "availableRoles"})
public class UserManagementController extends BaseUserManagementController{
	
	@Autowired
	private OfficeUserManagementService officeUsrMgmtService;
	
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

	@RequestMapping(value="/authentication/userMagement/preCreateUserAccount.do")
	public ModelAndView preCreateUserAccount(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/usrMgmt/accountCreate");
		mv.addObject("officeUserDto", new OfficeUserDTO());
		return mv;
	}
	
	@RequestMapping(value="/authentication/userMagement/createUserAccount.do")
	public ModelAndView createUserAccount(HttpServletRequest request, @ModelAttribute("officeUserDto")OfficeUserDTO officeUserDto){
		ModelAndView mv = new ModelAndView("redirect:/authentication/usrMgmt/initUsrMgmt.do");
		
		return null;
	}
}
