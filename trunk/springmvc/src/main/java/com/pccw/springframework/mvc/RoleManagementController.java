package com.pccw.springframework.mvc;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.convertor.OfficeRoleConvertor;
import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeRolePagedCriteria;
import com.pccw.springframework.service.OfficeRoleManagementService;
import com.pccw.springframework.validator.OfficeRoleValidator;

@Controller
@SessionAttributes({"officeRoleDto"})
public class RoleManagementController extends BaseController{
	
	@Autowired
	private OfficeRoleManagementService officeRoleMgmtService;
	
	@Autowired
	private OfficeRoleValidator officeRoleValidator;
	
	@RequestMapping("/authentication/roleMgmt/initRoleMgmt.do")
	public ModelAndView initRoleMgmt(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("authentication/roleMgmt/roleManagement");
		mv.addObject("officeRoleDto", new OfficeRoleDTO());
		return mv;
	}
	
	@RequestMapping("/authentication/roleMgmt/searchOfficeRoles.do")
	public ModelAndView searchRoles(HttpServletRequest request , @ModelAttribute("officeRoleDto")OfficeRoleDTO officeRoleDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("authentication/roleMgmt/roleManagement");
		
		officeRoleValidator.validateForEnquiry(officeRoleDto, errors);
		
		if(errors.hasErrors()){
			mv.addObject("officeRoleDto", officeRoleDto);
			return mv;
		}
		
		handleSearch(request , mv , officeRoleDto);
		return mv;
	}

	private void handleSearch(HttpServletRequest request , ModelAndView mv ,final OfficeRoleDTO officeRoleDto) {
		TableModel model = new TableModel("office_role_table",request);
		
		model.autoFilterAndSort(false);
		model.setItems(new PageItems(){
			public Collection<?> getItems(Limit limit) {
				OfficeRolePagedCriteria pagedCriteria = OfficeRoleConvertor.toPagedCriteria(officeRoleDto);
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(rowEnd);
				
				return officeRoleMgmtService.searchRolesByCriteria(pagedCriteria);
			}
			public int getTotalRows(Limit limit) {
				return officeRoleMgmtService.getOfficeRolesCountByCriteria(OfficeRoleConvertor.toPagedCriteria(officeRoleDto));
			}
		});
		
		model.setTable(getTable(request.getContextPath() , false ));
		
		mv.addObject("html", model.render());
	}

	private Table getTable(final String contextPath, boolean needCheckBox) {
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		table.setRow(row);
		
		HtmlColumn roleId = new HtmlColumn("roleId");
		roleId.setTitle("角色代号");
		roleId.setStyle("width:25% nowrap");
		roleId.setCellEditor(new CellEditor(){
			public Object getValue(Object item, String property, int rowcount) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("<a href=\"");
				buffer.append(contextPath + "/authentication/roleMgmt/viewDetail.do?ref=" + ((OfficeRoleDTO)item).getEncodedSysRefNum());
				buffer.append("\" >");
				buffer.append(((OfficeRoleDTO)item).getRoleId());
				buffer.append("</a>");
				return buffer.toString();
			}
		});
		row.addColumn(roleId);
		
		HtmlColumn roleName = new HtmlColumn("roleName");
		roleName.setTitle("角色名称");
		roleName.setStyle("width:25% nowrap");
		row.addColumn(roleName);
		
		HtmlColumn roleDesc = new HtmlColumn("roleDesc");
		roleDesc.setTitle("角色描述");
		roleDesc.setStyle("width:45% nowrap");
		row.addColumn(roleDesc);
		
		HtmlColumn status = new HtmlColumn("status");
		status.setTitle("状态");
		status.setStyle("width:5% nowrap");
		status.setCellEditor(new CellEditor(){
			public Object getValue(Object item, String property, int rowcount) {
				OfficeRoleDTO dto = (OfficeRoleDTO)item;
				if(CommonConstant.ACTIVE.equals(dto.getStatus())){
					return "启用";
				}else if(CommonConstant.INACTIVE.equals(dto.getStatus())){
					return "禁止";
				}
				return "";
			}
		});
		row.addColumn(status);
		return table;
	}
}
