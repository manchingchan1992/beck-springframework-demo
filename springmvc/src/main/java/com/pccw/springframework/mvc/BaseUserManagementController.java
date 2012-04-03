package com.pccw.springframework.mvc;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.HeaderEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.dto.JmesaCheckBoxDTO;
import com.pccw.springframework.dto.OfficeUserDTO;

public class BaseUserManagementController extends BaseController{
	
	protected Table getTable(HttpServletRequest request ,final JmesaCheckBoxDTO jmesaDto , boolean needCheckbox){
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		table.setRow(row);
		
		final String contextPath = request.getContextPath();
		
		if(needCheckbox){
			HtmlColumn checkbox = new HtmlColumn("select");
			checkbox.setStyle("width:8%");
			checkbox.setHeaderEditor(new HeaderEditor() {
				
				public Object getValue() {
					HtmlBuilder builder = new HtmlBuilder();
					
					String checkbox = null;
					if(jmesaDto.isSelectAll()){
						checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('usr_table')\" checked=\"checked\"/>";
					}else {
						checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('usr_table')\" />";
					}
					return builder.append(checkbox).toString();
				}
			});
			checkbox.setCellEditor(new CellEditor() {
				
				public Object getValue(Object item, String property, int rowCount) {
					OfficeUserDTO dto = (OfficeUserDTO)item;
					StringBuffer checkbox = new StringBuffer();
					if(jmesaDto.isSelectAll() || jmesaDto.isSelected(dto.getUserRecId())){	
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" checked=\"checked\" value=\"");
					}else {
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" value=\"");
					}
					checkbox.append(dto.getUserRecId());
					checkbox.append("\" />");
					checkbox.append("<input name=\"jmesaDto.currSelect\" type=\"checkbox\" value=\"");
					checkbox.append(dto.getUserRecId());
					checkbox.append("\" checked=\"checked\" style=\"display:none\" />&nbsp;&nbsp;");
				
					return checkbox.toString();
				}
			});
			row.addColumn(checkbox);
		}
		
		HtmlColumn loginId = new HtmlColumn("loginId");
		loginId.setTitle("用户名");
		loginId.setStyle("width:20% nowrap");
		loginId.setCellEditor(new CellEditor() {
			public Object getValue(Object item, String properties, int rowCount) {
				OfficeUserDTO dto = (OfficeUserDTO)item;
				StringBuffer buffer = new StringBuffer();
				buffer.append("<a href=\"");
				buffer.append(contextPath);
				buffer.append("authentication/usrMgmt/usrMaintenance.do?id=");
				buffer.append(dto.getUserRecId().trim());
				buffer.append("\">");
				buffer.append(dto.getLoginId());
				buffer.append("</a>");
				return buffer.toString();
			}
		});
		row.addColumn(loginId);
		
		HtmlColumn cnName = new HtmlColumn("cnName");
		cnName.setTitle("中文名");
		cnName.setStyle("width:20% nowrap");
		row.addColumn(cnName);
		
		HtmlColumn enName = new HtmlColumn("enName");
		enName.setTitle("英文名");
		enName.setStyle("width:20% nowrap");
		row.addColumn(enName);
		
		HtmlColumn email = new HtmlColumn("email");
		email.setTitle("邮箱");
		email.setStyle("width:30% nowrap");
		row.addColumn(email);
		
		HtmlColumn status = new HtmlColumn("accountStatus");
		status.setTitle("账户状态");
		status.setStyle("width:10% nowrap");
		status.setCellEditor(new CellEditor() {
			
			public Object getValue(Object item, String properties, int rowCount) {
				OfficeUserDTO dto = (OfficeUserDTO)item;
				
				if(CommonConstant.USER_ACCOUNT_ACTIVE.equals(dto.getAccountStatus())){
					return "启用";
				}else if(CommonConstant.USER_ACCOUNT_INACTIVE.equals(dto.getAccountStatus())){
					return "禁止";
				}
				return "";
			}
		});
		row.addColumn(status);
		
		return table;
	}
}
