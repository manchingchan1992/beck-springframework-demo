package com.pccw.springframework.convertor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.repository.EmailMessage;
import com.pccw.springframework.utility.SecurityUtils;

public class EmailMessageManagementConvertor {
	
	public static EmailMessageDTO toDto(EmailMessage msg){
		EmailMessageDTO dto = new EmailMessageDTO();
		
		try {
			if(msg == null){
				return dto;
			}
			
			BeanUtils.copyProperties(msg,dto);
			
			dto.setMessageFrom(StringUtils.isEmpty(msg.getMessageFrom()) ? "" : msg.getMessageFrom());
			dto.setMessageTo(StringUtils.isEmpty(msg.getMessageTo()) ? "" : msg.getMessageTo());
			dto.setMessageCc(StringUtils.isEmpty(msg.getMessageCc()) ? "" : msg.getMessageCc());
			dto.setMessageTitle(StringUtils.isEmpty(msg.getMessageTitle()) ? "" : msg.getMessageTitle());
			dto.setMessageContent(StringUtils.isEmpty(msg.getMessageContent()) ? "" : msg.getMessageContent());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public static EmailMessage toPo(EmailMessageDTO dto){
		EmailMessage msg = new EmailMessage();
		
		try {
			if(dto == null){
				return msg;
			}
			
			BeanUtils.copyProperties(dto, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public static EmailMessagePagedCriteria toOutBoxPagedCriteria(EmailMessageDTO dto){
		EmailMessagePagedCriteria pagedCriteria = new EmailMessagePagedCriteria();
		
		if(dto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		
		pagedCriteria.setMessageFrom(SecurityUtils.getUserEmail());
		
		return pagedCriteria;
	}
	
	public static EmailMessagePagedCriteria toInBoxPagedCriteria(EmailMessageDTO dto){
		EmailMessagePagedCriteria pagedCriteria = new EmailMessagePagedCriteria();
		
		if(dto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		
		pagedCriteria.setMessageTo(SecurityUtils.getUserEmail());
		
		return pagedCriteria;
	}
}
