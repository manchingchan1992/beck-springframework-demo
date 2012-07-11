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
import com.pccw.springframework.constant.DateFormatConstant;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.JmesaCheckBoxDTO;
import com.pccw.springframework.utility.DateUtils;
import com.pccw.springframework.utility.StringEncodeUtility;
import com.pccw.springframework.utility.URLUtils;

public class BaseMessageManagementController extends BaseController{
	
	protected Table getTableForOutbox(final HttpServletRequest request,boolean needCheckbox,final JmesaCheckBoxDTO jmesaDto ,final String contextPath){
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		table.setRow(row);
		
		if(needCheckbox){
			HtmlColumn checkbox = new HtmlColumn("select");
			checkbox.setStyle("width:8%");
			checkbox.setHeaderEditor(new HeaderEditor() {
				public Object getValue() {
					HtmlBuilder builder = new HtmlBuilder();
					String checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\"/>";
					return builder.append(checkbox).toString();
				}
			});
			checkbox.setCellEditor(new CellEditor() {	
				public Object getValue(Object item, String property, int rowCount) {
					EmailMessageDTO dto = (EmailMessageDTO)item;
					StringBuffer checkbox = new StringBuffer();
					if(jmesaDto.isSelected(dto.getSysRefMessage())){	
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" checked=\"checked\" value=\"");
					}else {
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" value=\"");
					}
					checkbox.append(dto.getSysRefMessage());
					checkbox.append("\" />");
					checkbox.append("<input name=\"jmesaDto.currSelect\" type=\"checkbox\" value=\"");
					checkbox.append(dto.getSysRefMessage());
					checkbox.append("\" checked=\"checked\" style=\"display:none\" />&nbsp;&nbsp;");
					return checkbox.toString();
				}
			});
			row.addColumn(checkbox);
		}
		
		HtmlColumn messageTo = new HtmlColumn("messageTo");
		messageTo.setTitle("收件人");
		messageTo.setStyle("width:30% nowrap");
		messageTo.setCellEditor(new CellEditor() {
			public Object getValue(Object item, String properties, int rowCount) {
				StringBuffer url = new StringBuffer();
				url.append(contextPath);
				url.append("/message/viewEmailMessageDetail.do?msg=");
				url.append(StringEncodeUtility.encode(((EmailMessageDTO)item).getSysRefMessage(), CommonConstant.STRING_ENCODE_BY_DEFAULT));
				url.append("&type=O");
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("<span><a href=\"");
				buffer.append(URLUtils.getHDIVUrl(request, url.toString()));
				buffer.append("\">");
				buffer.append(((EmailMessageDTO)item).getMessageTo());
				buffer.append("</a></span>");
				return buffer.toString();
			}
		});
		row.addColumn(messageTo);
		
		HtmlColumn title = new HtmlColumn("messageTitle");
		title.setTitle("主题");
		title.setStyle("width:50% nowrap");
		title.setCellEditor(new CellEditor() {
			public Object getValue(Object item, String properties, int rowCount) {
				return "<span>" + ((EmailMessageDTO)item).getMessageTitle() + "</span>";
			}
		});
		row.addColumn(title);
		
		HtmlColumn crDttm = new HtmlColumn("createDateTime");
		crDttm.setTitle("时间");
		crDttm.setStyle("width:22% nowrap");
		crDttm.setCellEditor(new CellEditor() {	
			public Object getValue(Object item, String properties, int rowCount) {
				EmailMessageDTO dto = (EmailMessageDTO)item;
				String date = DateUtils.formatDateTime(DateFormatConstant.DATE_FORMAT_WITH_LEFT_SLASH, dto.getCreateDateTime());
				return "<span>" + date + "</span>";
			}
		});
		row.addColumn(crDttm);
		
		return table;
	}
	
	protected Table getTableForInbox(final HttpServletRequest request ,boolean needCheckbox,final JmesaCheckBoxDTO jmesaDto ,final String contextPath){
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		table.setRow(row);
		
		if(needCheckbox){
			HtmlColumn checkbox = new HtmlColumn("select");
			checkbox.setStyle("width:8%");
			checkbox.setHeaderEditor(new HeaderEditor() {
				
				public Object getValue() {
					HtmlBuilder builder = new HtmlBuilder();
					
					String checkbox = null;
					if(jmesaDto.isSelectAll()){
						checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\" checked=\"checked\"/>";
					}else {
						checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\" />";
					}
					return builder.append(checkbox).toString();
				}
			});
			checkbox.setCellEditor(new CellEditor() {
				
				public Object getValue(Object item, String property, int rowCount) {
					EmailMessageDTO dto = (EmailMessageDTO)item;
					
					StringBuffer checkbox = new StringBuffer();
					if(jmesaDto.isSelectAll() || jmesaDto.isSelected(dto.getSysRefMessage())){	
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" checked=\"checked\" value=\"");
					}else {
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" value=\"");
					}
					checkbox.append(dto.getSysRefMessage());
					checkbox.append("\" />");
					checkbox.append("<input name=\"jmesaDto.currSelect\" type=\"checkbox\" value=\"");
					checkbox.append(dto.getSysRefMessage());
					checkbox.append("\" checked=\"checked\" style=\"display:none\" />&nbsp;&nbsp;");
					
					String imgPath = "";
					if(CommonConstant.NO.equals(dto.getIsRead())){
						imgPath = "<img src=\"" + contextPath + "/images/common/unread.gif\" />";
					}else {
						imgPath = "<img src=\"" + contextPath + "/images/common/read.gif\" />";
					}
					
					return checkbox.toString() + imgPath;
				}
			});
			row.addColumn(checkbox);
		}
		
		HtmlColumn messageFrom = new HtmlColumn("messageFrom");
		messageFrom.setTitle("发件人");
		messageFrom.setStyle("width:30% nowrap");
		messageFrom.setCellEditor(new CellEditor() {
			public Object getValue(Object item, String properties, int rowCount) {
				EmailMessageDTO dto = ((EmailMessageDTO)item);
				StringBuffer url = new StringBuffer();
				url.append(contextPath);
				url.append("/message/viewEmailMessageDetail.do?msg=");
				url.append(StringEncodeUtility.encode(((EmailMessageDTO)item).getSysRefMessage(), CommonConstant.STRING_ENCODE_BY_DEFAULT));
				url.append("&type=I");
				
				StringBuffer buffer = new StringBuffer();
				if(CommonConstant.YES.equals(dto.getIsRead())){
					buffer.append("<span>");
				}else {
					buffer.append("<span style=\"font-weight:bold\" >");
				}
				buffer.append("<a href=\"");
				buffer.append(URLUtils.getHDIVUrl(request, url.toString()));
				buffer.append("\">");
				buffer.append(((EmailMessageDTO)item).getMessageTo());
				buffer.append("</a></span>");
				return buffer.toString();
			}
		});
		row.addColumn(messageFrom);
		
		HtmlColumn title = new HtmlColumn("messageTitle");
		title.setTitle("主题");
		title.setStyle("width:50% nowrap");
		title.setCellEditor(new CellEditor() {
			public Object getValue(Object item, String properties, int rowCount) {
				EmailMessageDTO dto = ((EmailMessageDTO)item);
				if(CommonConstant.YES.equals(dto.getIsRead())){
					return "<span>" + ((EmailMessageDTO)item).getMessageTitle() + "</span>";
				}else {
					return "<span style=\"font-weight:bold\" >" + ((EmailMessageDTO)item).getMessageTitle() + "</span>";
				}
			}
		});
		row.addColumn(title);
		
		HtmlColumn crDttm = new HtmlColumn("createDateTime");
		crDttm.setTitle("时间");
		crDttm.setStyle("width:22% nowrap");
		crDttm.setCellEditor(new CellEditor() {
			
			public Object getValue(Object item, String properties, int rowCount) {
				EmailMessageDTO dto = (EmailMessageDTO)item;
				String date = DateUtils.formatDateTime(DateFormatConstant.DATE_FORMAT_WITH_LEFT_SLASH, dto.getCreateDateTime());
				if(CommonConstant.YES.equals(dto.getIsRead())){
					return "<span>" + date + "</span>";
				}else {
					return "<span style=\"font-weight:bold\" >" + date + "</span>";
				}
			}
		});
		row.addColumn(crDttm);
		
		return table;
	}
}
