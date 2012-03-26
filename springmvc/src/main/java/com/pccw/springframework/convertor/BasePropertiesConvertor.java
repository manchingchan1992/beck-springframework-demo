package com.pccw.springframework.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.springframework.dto.BaseDTO;
import com.pccw.springframework.repository.BaseEntity;

public class BasePropertiesConvertor {
	
	public static BaseDTO toDto (BaseEntity po , BaseDTO dto) {
	
		if(po != null){
			BeanUtils.copyProperties(po, dto);
		}
		
		return dto;
	}
	
	public static BaseEntity toPo(BaseEntity po , BaseDTO dto){

		if(dto != null){
			BeanUtils.copyProperties(dto, po);
		}
		
		return po;
	}
}
