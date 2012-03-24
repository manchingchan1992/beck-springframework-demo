package com.pccw.springframework.mvc;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.pccw.springframework.convertor.EmailMessageManagementConvertor;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.service.MessageManagementService;
import com.pccw.springframework.utility.SecurityUtils;
import com.pccw.springframework.validator.EmailMessageValidator;

@Controller
@SessionAttributes(value="emailMessageDTO")
public class MessageManagementController extends MessageManagementBaseController{
	
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
		ModelAndView mv = new ModelAndView("redirect:/message/outboxSearch.do");
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
	
	@RequestMapping(value="/message/initInbox.do")
	public ModelAndView initInbox(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("redirect:/message/inboxSearch.do");
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
		mv.addObject("emailMessageDTO",this.messageManagementService.viewMessageDetail(sysRefMessage,type));
		if (CommonConstant.OUTBOX.equals(type)){
			mv.addObject("back",request.getContextPath() + "/message/initOutbox.do");
		}else if (CommonConstant.RECYCLE.equals(type)){
			mv.addObject("back",request.getContextPath() + "/message/initRecyclebox.do");
		}else {
			mv.addObject("back",request.getContextPath() + "/message/initInbox.do");
		}
		return mv;
	}
	
	@RequestMapping(value="/message/emailMessageDelete.do")
	public ModelAndView emailMessageDelete(HttpServletRequest request){
		return null;
	}

	private void handleSearch(HttpServletRequest request,ModelAndView mv ,
			final EmailMessageDTO emailMessageDTO , final String boxType) {
		String tableId = "messageBox";
		
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
		
		String contextPath = request.getContextPath();
		if(CommonConstant.INBOX.equals(boxType)){
			model.setTable(getTableForInbox(true, contextPath));
		}else if(CommonConstant.OUTBOX.equals(boxType)){
			model.setTable(getTableForOutbox(true, contextPath));
		}
		
		mv.addObject("html",model.render());
	}	
}
