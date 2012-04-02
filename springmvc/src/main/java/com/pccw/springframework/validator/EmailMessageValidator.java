package com.pccw.springframework.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.constant.MessageConstant;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.service.OfficeUserManagementService;

@Component(value="emailMessageValidator")
public class EmailMessageValidator extends BaseValidator{
	
	@Autowired
	private OfficeUserManagementService officeUsrMgmtService;
	
	public boolean supports(Class<?> clazz) {
		return EmailMessageDTO.class.equals(clazz);
	}
	
	public void validate(Object dto, Errors errors) {
		EmailMessageDTO messageDto = (EmailMessageDTO)dto;
		validateMessageTo(messageDto,errors);
		validateMessageCc(messageDto,errors);
		validateMessageTitle(messageDto,errors);
		validateMessageContent(messageDto,errors);
	}

	private void validateMessageTo(EmailMessageDTO dto, Errors errors) {
		String email = dto.getMessageTo().trim();
		
		if(StringUtils.isEmpty(email)){
			errors.rejectValue("messageTo", MessageConstant.KEY_ERROR_EMAIL_TO_REQUIRED, null, MessageConstant.DEFAULT_ERROR_MSG_EMAIL_TO_REQUIRED);
		}else {			
			String suffix = email.substring(email.lastIndexOf("@")+1, email.length());
			if(!EmailValidator.getInstance().isValid(email) || 
					!CommonConstant.EMAIL_SUFFIX.equals(suffix)){
				errors.rejectValue("messageTo", MessageConstant.KEY_ERROR_INVALID_EMAIL_TO, null,MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL_TO);
			}
			
			OfficeUserDTO userDto = officeUsrMgmtService.getOfficeUserByEmail(email);
			
			if(userDto == null){
				errors.rejectValue("messageTo", MessageConstant.KEY_ERROR_NOT_EXIST_EMAIL_TO, null,MessageConstant.DEFAULT_ERR_MSG_NOT_EXIST_EMAIL_TO);
			}
		}	
	}
	
	private void validateMessageCc(EmailMessageDTO dto , Errors errors){
		String email = dto.getMessageCc().trim();
		
		if(!StringUtils.isEmpty(email)){			
			String suffix = email.substring(email.lastIndexOf("@")+1, email.length());
			if(!EmailValidator.getInstance().isValid(email) || 
					!CommonConstant.EMAIL_SUFFIX.equals(suffix)){
				errors.rejectValue("messageCc", MessageConstant.KEY_ERROR_INVALID_EMAIL_CC, null, MessageConstant.DEFAULT_ERR_MSG_IVALID_EMAIL_CC);
			}
			
			OfficeUserDTO userDto = officeUsrMgmtService.getOfficeUserByEmail(email);
			
			if(userDto == null){
				errors.rejectValue("messageCc", MessageConstant.KEY_ERROR_NOT_EXIST_EMAIL_CC,null,MessageConstant.DEFAULT_ERR_MSG_NOT_EXIST_EMAIL_CC);
			}
		}
	}

	private void validateMessageTitle(EmailMessageDTO dto, Errors errors) {
		validateStringLength(dto, errors, "messageTitle", MessageConstant.KEY_ERROR_INVALID_EMAIL_TITLE_LENGTH, MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL_TITLE_LENGTH, 30);
	}

	private void validateMessageContent(EmailMessageDTO dto, Errors errors) {
		validateStringLength(dto, errors, "messageContent", MessageConstant.KEY_ERROR_INVALID_EMAIL_CONTENT_LENGTH, MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL_CONTENT_LENGTH, 4000);
	}
}
