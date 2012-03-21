package com.pccw.springframework.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.repository.EmailMessage;

public class EmailMessageManagementConvertor {
	
	public static EmailMessageDTO toDto(EmailMessage msg){
		EmailMessageDTO dto = new EmailMessageDTO();
		
		try {
			if(msg == null){
				return dto;
			}
			
			BeanUtils.copyProperties(msg,dto);
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
	
	public static EmailMessagePagedCriteria toPagedCriteria(EmailMessageDTO dto){
		EmailMessagePagedCriteria pagedCriteria = new EmailMessagePagedCriteria();
		
		if(dto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		pagedCriteria.setMessageFrom("Beck.BQ.Lu@pccw.com");
		
		return pagedCriteria;
	}
}
