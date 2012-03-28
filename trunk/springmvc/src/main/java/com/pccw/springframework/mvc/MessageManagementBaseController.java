package com.pccw.springframework.mvc;

import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.HeaderEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.event.RowEvent;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.constant.DateFormatConstant;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.utility.DateUtils;

public class MessageManagementBaseController extends BaseController{
	
	protected Table getTableForOutbox(boolean needCheckbox,final String contextPath){
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.onclick(new RowEvent() {	
			public String execute(Object item, int rowCount) {	
				return "window.location='" + contextPath + "/message/viewEmailMessageDetail.do?sysRefMsg=" + ((EmailMessageDTO)item).getSysRefMessage() + "&type=O'";
			}
		});
		table.setRow(row);
		
		if(needCheckbox){
			HtmlColumn checkbox = new HtmlColumn("select");
			checkbox.setStyle("width:8%");
			checkbox.setHeaderEditor(new HeaderEditor() {
				public Object getValue() {
					HtmlBuilder builder = new HtmlBuilder();
					String checkbox = "<input type=\"checkbox\" style=\"align:center\"/>";
					return builder.append(checkbox).toString();
				}
			});
			checkbox.setCellEditor(new CellEditor() {	
				public Object getValue(Object item, String property, int rowCount) {
					EmailMessageDTO dto = (EmailMessageDTO)item;
					String checkbox = "<input type=\"checkbox\" value=\"" + dto.getSysRefMessage() +"\" />&nbsp;&nbsp;";
					return checkbox;
				}
			});
			row.addColumn(checkbox);
		}
		
		HtmlColumn messageTo = new HtmlColumn("messageTo");
		messageTo.setTitle("收件人");
		messageTo.setStyle("width:30% nowrap");
		messageTo.setCellEditor(new CellEditor() {
			public Object getValue(Object item, String properties, int rowCount) {
				return "<span>" + ((EmailMessageDTO)item).getMessageTo() + "</span>";
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
	
	protected Table getTableForInbox(boolean needCheckbox,final String contextPath){
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.onclick(new RowEvent() {	
			public String execute(Object item, int rowCount) {	
				return "window.location='" + contextPath + "/message/viewEmailMessageDetail.do?sysRefMsg=" + ((EmailMessageDTO)item).getSysRefMessage() + "&type=I'";
			}
		});
		table.setRow(row);
		
		if(needCheckbox){
			HtmlColumn checkbox = new HtmlColumn("select");
			checkbox.setStyle("width:8%");
			checkbox.setHeaderEditor(new HeaderEditor() {
				
				public Object getValue() {
					HtmlBuilder builder = new HtmlBuilder();
					String checkbox = "<input type=\"checkbox\" style=\"align:center\"/>";
					return builder.append(checkbox).toString();
				}
			});
			checkbox.setCellEditor(new CellEditor() {
				
				public Object getValue(Object item, String property, int rowCount) {
					EmailMessageDTO dto = (EmailMessageDTO)item;
					String checkbox = "<input type=\"checkbox\" value=\"" + dto.getSysRefMessage() +"\" />&nbsp;&nbsp;";
					
					String imgPath = "";
					if(CommonConstant.NO.equals(dto.getIsRead())){
						imgPath = "<img src=\"" + contextPath + "/images/common/unread.gif\" />";
					}else {
						imgPath = "<img src=\"" + contextPath + "/images/common/read.gif\" />";
					}
					
					checkbox = checkbox + imgPath;
					return checkbox;
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
				if(CommonConstant.YES.equals(dto.getIsRead())){
					return "<span>" + ((EmailMessageDTO)item).getMessageFrom() + "</span>";
				}else {
					return "<span style=\"font-weight:bold\" >" + ((EmailMessageDTO)item).getMessageFrom() + "</span>";
				}
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