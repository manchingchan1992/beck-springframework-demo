package com.pccw.springframework.mvc;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.HeaderEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.event.RowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.constant.DateFormatConstant;
import com.pccw.springframework.convertor.EmailMessageManagementConvertor;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.service.MessageManagementService;
import com.pccw.springframework.utility.DateUtils;
import com.pccw.springframework.utility.SecurityUtils;
import com.pccw.springframework.validator.EmailMessageValidator;

@Controller
@SessionAttributes(value="emailMessageDTO")
public class MessageManagementController extends BaseController{
	
	@Autowired
	private EmailMessageValidator emailMessageValidator;
	
	@Autowired
	private MessageManagementService messageManagementService;
	
	@RequestMapping(value="/message/initEmail.do")
	public ModelAndView initNewEmail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("message/createEmail");
		EmailMessageDTO dto = new EmailMessageDTO();
		mv.addObject("emailMessageDTO", dto);
		return mv;
	}
	
	@RequestMapping(value="/message/sendEmail.do")
	public ModelAndView sendEmail(HttpServletRequest request,
			@ModelAttribute(value="emailMessageDTO")EmailMessageDTO emailMessageDTO,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("redirect:/message/initOutbox.do");
		logger.info(">>>>>Send Email Begin .....");
		emailMessageValidator.validate(emailMessageDTO, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("message/createEmail");
			mv.addObject("emailMessageDTO", emailMessageDTO);
			return mv;
		}
		
		emailMessageDTO.setMessageFrom(SecurityUtils.getUserEmail());
		messageManagementService.sendEmail(emailMessageDTO);
		return mv;
	}
	
	@RequestMapping(value="/message/initOutbox.do")
	public ModelAndView initOutbox(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("message/outbox");
		EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
		mv.addObject("emailMessageDTO", emailMessageDTO);
		return mv;
	}
	
	@RequestMapping(value="/message/outboxSearch.do")
	public ModelAndView outboxSearch(HttpServletRequest request,@ModelAttribute(value="emailMessageDTO")EmailMessageDTO emailMessageDTO){
		ModelAndView mv = new ModelAndView("message/outbox");
		handleSearch(request,mv,emailMessageDTO,CommonConstant.OUTBOX);
		return mv;
	}

	private void handleSearch(HttpServletRequest request,ModelAndView mv ,
			final EmailMessageDTO emailMessageDTO , final String boxType) {
		String tableId = "messageBox";
		EmailMessagePagedCriteria pagedCriteria = null;
		if(CommonConstant.INBOX.equals(boxType)){
			tableId = "_inbox";
			pagedCriteria = EmailMessageManagementConvertor.toInBoxPagedCriteria(emailMessageDTO);
		}else if(CommonConstant.OUTBOX.equals(boxType)){
			tableId = "_outbox";
			pagedCriteria = EmailMessageManagementConvertor.toOutBoxPagedCriteria(emailMessageDTO);
		}
		
		TableModel model = new TableModel(tableId, request);
		model.autoFilterAndSort(false);
		
		model.setItems(new PageItems() {
			
			private EmailMessagePagedCriteria toPagedCriteria(){
				if(CommonConstant.INBOX.equals(boxType)){
					return EmailMessageManagementConvertor.toInBoxPagedCriteria(emailMessageDTO);
				}else if(CommonConstant.OUTBOX.equals(boxType)){
					return EmailMessageManagementConvertor.toOutBoxPagedCriteria(emailMessageDTO);
				}
				return new EmailMessagePagedCriteria();
			}
			
			public int getTotalRows(Limit limit) {
				Integer totalRows = messageManagementService.getMessagesCountForSearch(toPagedCriteria());
				return totalRows==null ? 0 : totalRows.intValue();
			}
			
			public Collection<?> getItems(Limit limit) {
				EmailMessagePagedCriteria pagedCriteria = toPagedCriteria();
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(rowEnd);
				
				List<EmailMessageDTO> messages = messageManagementService.getMessagesForSearch(pagedCriteria);
				return messages;
			}
		});
		
		model.setTable(getTable(request ,true , boxType));
		
		mv.addObject("html",model.render());
	}

	private Table getTable(final HttpServletRequest request,boolean needCheckbox ,final String boxType) {
		HtmlTable table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.onclick(new RowEvent() {
			
			public String execute(Object item, int rowCount) {	
				return "window.location='" + request.getContextPath() + "/message/viewEmailMessageDetail.do?sysRefMsg=" + ((EmailMessageDTO)item).getSysRefMessage() + "&type=" + boxType+ "'";
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
					String checkbox = "<input type=\"checkbox\" value=\"" + dto.getSysRefMessage() +"\" />";
					return checkbox;
				}
			});
			row.addColumn(checkbox);
		}
		
		if(CommonConstant.INBOX.equals(boxType)){
			HtmlColumn messageFrom = new HtmlColumn("messageFrom");
			messageFrom.setTitle("发件人");
			messageFrom.setStyle("width:30% nowrap");
			row.addColumn(messageFrom);
		}else if(CommonConstant.OUTBOX.equals(boxType)){			
			HtmlColumn messageTo = new HtmlColumn("messageTo");
			messageTo.setTitle("收件人");
			messageTo.setStyle("width:30% nowrap");
			row.addColumn(messageTo);
		}
		
		HtmlColumn title = new HtmlColumn("messageTitle");
		title.setTitle("主题");
		title.setStyle("width:50% nowrap");
		row.addColumn(title);
		
		HtmlColumn crDttm = new HtmlColumn("createDateTime");
		crDttm.setTitle("时间");
		crDttm.setStyle("width:22% nowrap");
		crDttm.setCellEditor(new CellEditor() {
			
			public Object getValue(Object item, String properties, int rowCount) {
				EmailMessageDTO dto = (EmailMessageDTO)item;
				String date = DateUtils.formatDateTime(DateFormatConstant.DATE_FORMAT_WITH_LEFT_SLASH, dto.getCreateDateTime());
				return date;
			}
		});
		row.addColumn(crDttm);
		
		return table;
	}
	
	@RequestMapping(value="/message/initInbox.do")
	public ModelAndView initInbox(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("message/inbox");
		EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
		mv.addObject("emailMessageDTO", emailMessageDTO);
		return mv;
	}
	
	@RequestMapping(value="/message/inboxSearch.do")
	public ModelAndView inboxSearch(HttpServletRequest request,@ModelAttribute(value="emailMessageDTO")EmailMessageDTO emailMessageDTO){
		ModelAndView mv = new ModelAndView("message/inbox");
		handleSearch(request,mv,emailMessageDTO,CommonConstant.INBOX);
		return mv;
	}
	
	@RequestMapping(value="/message/viewEmailMessageDetail.do")
	public ModelAndView viewEmailMessageDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("message/viewMessageDetail");
		String sysRefMessage = request.getParameter("sysRefMsg");
		String type = request.getParameter("type");
		mv.addObject("emailMessageDTO",this.messageManagementService.viewMessageDetail(sysRefMessage));
		if(CommonConstant.INBOX.equals(type)){
			mv.addObject("back",request.getContextPath() + "/message/inboxSearch.do");
		}else if (CommonConstant.OUTBOX.equals(type)){
			mv.addObject("back",request.getContextPath() + "/message/outboxSearch.do");
		}else if (CommonConstant.RECYCLE.equals(type)){
			mv.addObject("back",request.getContextPath() + "/message/recycleboxSearch.do");
		}else {
			mv.addObject("back",request.getContextPath() + "/message/inboxSearch.do");
		}
		return mv;
	}
	
}
