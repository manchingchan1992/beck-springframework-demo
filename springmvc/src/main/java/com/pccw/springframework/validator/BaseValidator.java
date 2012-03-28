package com.pccw.springframework.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pccw.springframework.constant.MessageConstant;

public abstract class BaseValidator implements Validator{
	
	protected void validateStringLength (Object dto, Errors errors, String fieldName, String messageKey,String defaultMessage , int maxLength){
		String property = null;
		try {
			property = BeanUtils.getProperty(dto, fieldName);
			
			if(StringUtils.isEmpty(property)){
				return ;
			}
			
			//check chinese and replace with **
			String chsRegex = "[\u4e00-\u9fa5]";
			if(matches(chsRegex ,property)) {
				property = property.replaceAll(chsRegex, "**");
			}
			
			if(property.trim().length() > maxLength){
				errors.rejectValue(fieldName, messageKey, null, defaultMessage);
			}
		}catch (Exception e){
			e.printStackTrace();
			errors.rejectValue(fieldName, MessageConstant.KEY_SYSTEM_ERROR, null,MessageConstant.DEFAULT_SYSTEM_ERROR_MSG);
		}
	}
	
	protected boolean matches( String regex ,String input){
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(input);
		return m.find();
	}

}
