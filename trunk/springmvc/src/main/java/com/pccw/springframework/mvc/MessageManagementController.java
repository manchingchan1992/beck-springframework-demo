package com.pccw.springframework.mvc;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.pccw.springframework.constant.ActionFlag;
import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.convertor.EmailMessageManagementConvertor;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessageEnquireDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.service.MessageManagementService;
import com.pccw.springframework.utility.SecurityUtils;
import com.pccw.springframework.validator.EmailMessageValidator;

@Controller
@SessionAttributes(value={"emailMessageDTO" , "emailMessageEnquireDto"})
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
		EmailMessageEnquireDTO enquireDto = new EmailMessageEnquireDTO();
		mv.addObject("emailMessageEnquireDto", enquireDto);
		request.getSession().setAttribute(ActionFlag.ACTION_FLAG, ActionFlag.SEARCH);
		return mv;
	}
	
	@RequestMapping(value="/message/outboxSearch.do")
	public ModelAndView outboxSearch(HttpServletRequest request,@ModelAttribute(value="emailMessageEnquireDto")EmailMessageEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("message/outbox");
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(!StringUtils.isEmpty(actionFlag)){
			request.getSession().setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}else {
			actionFlag = (String)request.getSession().getAttribute(ActionFlag.ACTION_FLAG);
		}
		
		if(ActionFlag.PAGING.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelected();
		}else if(ActionFlag.SELECT_ALL.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelectAll();
		}else {
			enquireDto.getJmesaDto().resetJmesa();
			iniAllSelectOption(enquireDto , CommonConstant.OUTBOX);
		}
		handleSearch(request,mv,enquireDto,CommonConstant.OUTBOX);
		return mv;
	}
	
	@RequestMapping(value="/message/initInbox.do")
	public ModelAndView initInbox(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectView("/message/inboxSearch.do" , true));
		EmailMessageEnquireDTO enquireDto = new EmailMessageEnquireDTO();
		mv.addObject("emailMessageEnquireDto", enquireDto);
		request.getSession().setAttribute(ActionFlag.ACTION_FLAG, ActionFlag.SEARCH);
		return mv;
	}
	
	@RequestMapping(value="/message/inboxSearch.do")
	public ModelAndView inboxSearch(HttpServletRequest request,@ModelAttribute(value="emailMessageEnquireDto")EmailMessageEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("message/inbox");
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(!StringUtils.isEmpty(actionFlag)){
			request.getSession().setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}else {
			actionFlag = (String)request.getSession().getAttribute(ActionFlag.ACTION_FLAG);
		}
		
		if(ActionFlag.PAGING.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelected();
		}else if(ActionFlag.SELECT_ALL.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelectAll();
		}else {
			enquireDto.getJmesaDto().resetJmesa();
			iniAllSelectOption(enquireDto , CommonConstant.INBOX);
		}
		handleSearch(request,mv,enquireDto,CommonConstant.INBOX);
		return mv;
	}
	
	@RequestMapping(value="/message/viewEmailMessageDetail.do")
	public ModelAndView viewEmailMessageDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("message/viewMessageDetail");
		String sysRefMessage = request.getParameter("msg");
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
	
	@RequestMapping(value="/message/deleteEmail.do")
	public void emailMessageDelete(HttpServletRequest request , HttpServletResponse response ,@ModelAttribute(value="emailMessageEnquireDto")EmailMessageEnquireDTO enquireDto){
		response.setContentType("text/plain");
		
		try{
			String[] selects = enquireDto.getJmesaDto().getSelect();
			if(selects != null && selects.length != 0){
				for(int i=0 ; i<selects.length ; i++){
					messageManagementService.deleteEmailMessage(selects[i], false);
				}
				response.getWriter().write("{\"success\":\"true\"}");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void handleSearch(HttpServletRequest request,ModelAndView mv ,
			final EmailMessageEnquireDTO enquireDto , final String boxType) {
		String tableId = "messageBox";
		
		TableModel model = new TableModel(tableId, request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			private EmailMessagePagedCriteria toPagedCriteria(){
				if(CommonConstant.INBOX.equals(boxType)){
					return EmailMessageManagementConvertor.toInBoxPagedCriteria(enquireDto);
				}else if(CommonConstant.OUTBOX.equals(boxType)){
					return EmailMessageManagementConvertor.toOutBoxPagedCriteria(enquireDto);
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
			model.setTable(getTableForInbox(true, enquireDto.getJmesaDto() ,contextPath));
		}else if(CommonConstant.OUTBOX.equals(boxType)){
			model.setTable(getTableForOutbox(true, enquireDto.getJmesaDto() , contextPath));
		}
		
		mv.addObject("html",model.render());
		mv.addObject("emailMessageEnquireDto",enquireDto);
	}	
	
	private void iniAllSelectOption(EmailMessageEnquireDTO enquireDto , String boxType){
		EmailMessagePagedCriteria pagedCriteria = null ;
		
		if(CommonConstant.OUTBOX.equals(boxType)){
			pagedCriteria = EmailMessageManagementConvertor.toOutBoxPagedCriteria(enquireDto);
		}else if(CommonConstant.RECYCLE.equals(boxType)){
			
		}else {
			pagedCriteria = EmailMessageManagementConvertor.toInBoxPagedCriteria(enquireDto);
		}
		pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(0);
		pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(0);
		List<EmailMessageDTO> messages = messageManagementService.getMessagesForSearch(pagedCriteria);
		String[] msgRefs = new String[messages.size()];
		if(!CollectionUtils.isEmpty(messages)){
			for(int index = 0 ; index < messages.size() ; index++){
				msgRefs[index] = messages.get(index).getSysRefMessage();
			}
		}
		enquireDto.getJmesaDto().initAllSelectOption(msgRefs);
	}
}
