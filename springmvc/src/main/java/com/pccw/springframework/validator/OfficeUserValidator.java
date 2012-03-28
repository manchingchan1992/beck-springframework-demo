package com.pccw.springframework.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.pccw.springframework.dto.OfficeUserDTO;

public class OfficeUserValidator extends BaseValidator{

	public boolean supports(Class<?> clazz) {
		return OfficeUserDTO.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		OfficeUserDTO dto = (OfficeUserDTO)object;
		
		if(dto == null){
			return ;
		}
		
		if(StringUtils.isEmpty(dto.getLoginId())){
			
		}
	}

}
