package com.pccw.springframework.convertor;

import org.apache.commons.beanutils.BeanUtils;

import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.repository.OfficeUser;

public class OfficeUserConvertor {
	
	public static OfficeUserDTO toDto(OfficeUser usr){
		OfficeUserDTO dto = new OfficeUserDTO();
		try {
			if(usr == null){
				return dto;
			}
			
			BeanUtils.copyProperties(dto, usr);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public static OfficeUser toPo(OfficeUserDTO dto){
		OfficeUser usr = new OfficeUser();
		
		try {
			if(dto == null){
				return usr;
			}
			
			BeanUtils.copyProperties(usr, dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usr;
	}
}
