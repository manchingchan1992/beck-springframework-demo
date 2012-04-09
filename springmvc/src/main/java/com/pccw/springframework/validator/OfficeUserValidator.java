package com.pccw.springframework.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.constant.MessageConstant;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.service.OfficeUserManagementService;

@Component("officeUserValidator")
public class OfficeUserValidator extends BaseValidator{
	
	@Autowired
	private OfficeUserManagementService officeUsrMgmtService;

	public boolean supports(Class<?> clazz) {
		return OfficeUserDTO.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		
	}
	
	public void validate(Object object , Errors errors , boolean isUpdate){
		OfficeUserDTO dto = (OfficeUserDTO)object;
		if(dto == null){
			return ;
		}
		
		if(StringUtils.isEmpty(dto.getLoginId())){
			errors.rejectValue("loginId", MessageConstant.KEY_ERROR_LOGIN_ID_REQUIRED, null, MessageConstant.DEFAULT_ERR_MSG_LOGIN_ID_REQUIRED);
		}else {
			//validate login id existing
			if(!isUpdate){
				OfficeUserDTO officeUserDto = officeUsrMgmtService.getUserByLoginId(dto.getLoginId());
				if(officeUserDto != null){
					errors.rejectValue("loginId", MessageConstant.KEY_ERROR_EXISTED_LOGINID, null, MessageConstant.DEFAULT_ERR_MSG_EXISTED_LOGINID);
				}
			}
		}
		
		validateStringLength(dto, errors, "enName", MessageConstant.KEY_ERROR_EN_NAME_MAX_LENGTH, MessageConstant.DEFAULT_ERR_MSG_CN_NAME_MAX_LENGTH, 20);
		validateStringLength(dto, errors, "cnName", MessageConstant.KEY_ERROR_CN_NAME_MAX_LENGTH, MessageConstant.DEFAULT_ERR_MSG_CN_NAME_MAX_LENGTH, 20);
		
		if(StringUtils.isEmpty(dto.getAccountStatus())){
			errors.rejectValue("accountStatus", MessageConstant.KEY_ERROR_ACC_ST_REQUIRED, null, MessageConstant.DEFAULT_ERR_MSG_ACC_ST_REQUIRED);
		}
		
		if(StringUtils.isEmpty(dto.getEmail())){
			errors.rejectValue("email", MessageConstant.KEY_ERROR_EMAIL_REQUIRED, null, MessageConstant.DEFAULT_ERR_MSG_EMAIL_REQUIRED);
		}else {			
			String email = dto.getEmail();
			String suffix = email.substring(email.lastIndexOf("@")+1, email.length());
			if(!EmailValidator.getInstance().isValid(email) || 
					!CommonConstant.EMAIL_SUFFIX.equals(suffix)){
				errors.rejectValue("email", MessageConstant.KEY_ERROR_INVALID_EMAIL, null,MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL);
			}
			
			OfficeUserDTO userDto = officeUsrMgmtService.getOfficeUserByEmail(email);
			
			if(userDto != null && !email.equals(userDto.getEmail())){
				errors.rejectValue("email", MessageConstant.KEY_ERROR_EXISTED_EMAIL, null,MessageConstant.DEFAULT_ERR_MSG_EXISTED_EMAIL);
			}
		}
	}

}
