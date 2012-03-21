package com.pccw.springframework.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.constant.MessageConstant;
import com.pccw.springframework.dto.EmailMessageDTO;

@Component(value="emailMessageValidator")
public class EmailMessageValidator implements Validator{
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
		}
	}

	private void validateMessageTitle(EmailMessageDTO dto, Errors errors) {
		String title = dto.getMessageTitle().trim();
		if(title.length() > 30){
			errors.rejectValue("messageTitle", MessageConstant.KEY_ERROR_INVALID_EMAIL_TITLE_LENGTH, null, MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL_TITLE_LENGTH);
		}
	}

	private void validateMessageContent(EmailMessageDTO dto, Errors errors) {
		String content = dto.getMessageContent().trim();
		if(content.length() > 4000){
			errors.rejectValue("messageContent", MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL_CONTENT_LENGTH);
			errors.rejectValue("messageContent", MessageConstant.KEY_ERROR_INVALID_EMAIL_CONTENT_LENGTH, null, MessageConstant.DEFAULT_ERR_MSG_INVALID_EMAIL_CONTENT_LENGTH);
		}
	}
}
