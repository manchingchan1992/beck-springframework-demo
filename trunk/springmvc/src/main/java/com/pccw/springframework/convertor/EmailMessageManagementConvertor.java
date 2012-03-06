package com.pccw.springframework.convertor;

import org.apache.commons.beanutils.BeanUtils;

import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.repository.EmailMessage;

public class EmailMessageManagementConvertor {
	
	public static EmailMessageDTO toDto(EmailMessage msg){
		EmailMessageDTO dto = new EmailMessageDTO();
		
		try {
			if(msg == null){
				return dto;
			}
			
			BeanUtils.copyProperties(dto, msg);
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
			
			BeanUtils.copyProperties(msg, dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
}
