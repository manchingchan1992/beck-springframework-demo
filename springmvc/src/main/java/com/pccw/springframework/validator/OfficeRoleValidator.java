package com.pccw.springframework.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.springframework.constant.MessageConstant;
import com.pccw.springframework.dto.OfficeRoleDTO;

@Component("officeRoleValidator")
public class OfficeRoleValidator extends BaseValidator{

	public boolean supports(Class<?> clazz) {
		return OfficeRoleDTO.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		
	}
	
	public void validateForEnquiry(Object object , Errors errors){
		OfficeRoleDTO dto = (OfficeRoleDTO)object;
		if(!StringUtils.isEmpty(dto.getRoleId())){
			if(!dto.getRoleId().startsWith("ROLE_")){
				errors.rejectValue("roleId" , MessageConstant.KEY_ERROR_INVALID_ROLE_ID , null , MessageConstant.DEFAULT_ERR_MSG_INVALID_ROLE_ID);
			}
		}
	}

}
