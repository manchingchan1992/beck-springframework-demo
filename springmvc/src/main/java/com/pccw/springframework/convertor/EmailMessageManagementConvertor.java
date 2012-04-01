package com.pccw.springframework.convertor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessageEnquireDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.repository.EmailMessage;
import com.pccw.springframework.utility.SecurityUtils;

public class EmailMessageManagementConvertor {
	
	public static EmailMessageDTO toDto(EmailMessage msg){
		EmailMessageDTO dto = new EmailMessageDTO();
		
		if(msg == null){
			return dto;
		}
		
		BasePropertiesConvertor.toDto(msg , dto);
		
		dto.setSysRefMessage(StringUtils.isEmpty(msg.getSysRefMessage()) ? "" : msg.getSysRefMessage());
		dto.setMessageFrom(StringUtils.isEmpty(msg.getMessageFrom()) ? "" : msg.getMessageFrom());
		dto.setMessageTo(StringUtils.isEmpty(msg.getMessageTo()) ? "" : msg.getMessageTo());
		dto.setMessageCc(StringUtils.isEmpty(msg.getMessageCc()) ? "" : msg.getMessageCc());
		dto.setMessageTitle(StringUtils.isEmpty(msg.getMessageTitle()) ? "" : msg.getMessageTitle());
		dto.setMessageContent(StringUtils.isEmpty(msg.getMessageContent()) ? "" : msg.getMessageContent());
		dto.setIsRead(StringUtils.isEmpty(msg.getIsRead()) ? "" : msg.getIsRead());
		
		return dto;
	}
	
	public static EmailMessage toPo(EmailMessageDTO dto){
		
		if(dto == null){
			return null;
		}
		
		EmailMessage msg = new EmailMessage();
		
		BasePropertiesConvertor.toPo(msg , dto);
		
		msg.setSysRefMessage(StringUtils.isEmpty(dto.getSysRefMessage()) ? "" : dto.getSysRefMessage());
		msg.setMessageFrom(StringUtils.isEmpty(dto.getMessageFrom()) ? "" : dto.getMessageFrom());
		msg.setMessageTo(StringUtils.isEmpty(dto.getMessageTo()) ? "" : dto.getMessageTo());
		msg.setMessageCc(StringUtils.isEmpty(dto.getMessageCc()) ? "" : dto.getMessageCc());
		msg.setMessageTitle(StringUtils.isEmpty(dto.getMessageTitle()) ? "" : dto.getMessageTitle());
		msg.setMessageContent(StringUtils.isEmpty(dto.getMessageContent()) ? "" : dto.getMessageContent());
		msg.setIsRead(StringUtils.isEmpty(dto.getIsRead()) ? "" : dto.getIsRead());
		
		return msg;
	}
	
	public static EmailMessagePagedCriteria toOutBoxPagedCriteria(EmailMessageEnquireDTO dto){
		EmailMessagePagedCriteria pagedCriteria = new EmailMessagePagedCriteria();
		
		if(dto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		
		pagedCriteria.setMessageFrom(SecurityUtils.getUserEmail());
		
		if(dto.getJmesaDto() != null && dto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(dto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(dto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
	
	public static EmailMessagePagedCriteria toInBoxPagedCriteria(EmailMessageEnquireDTO dto){
		EmailMessagePagedCriteria pagedCriteria = new EmailMessagePagedCriteria();
		
		if(dto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		
		pagedCriteria.setMessageTo(SecurityUtils.getUserEmail());
		
		if(dto.getJmesaDto() != null && dto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(dto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(dto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
}
