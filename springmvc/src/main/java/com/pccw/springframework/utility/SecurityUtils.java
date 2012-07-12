package com.pccw.springframework.utility;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.dto.OfficeUserDTO;

public class SecurityUtils {
	
	public static String getUserRecId(){
		UserDetails user = getUser();
		if(user != null && !StringUtils.isEmpty(((OfficeUserDTO)user).getUserRecId())){
			return ((OfficeUserDTO)user).getUserRecId();
		}else {
			return CommonConstant.ANOYMOUS_OFFICE_USER;
		}
	}
	
	public static String getUserEmail(){
		UserDetails user = getUser();
		if(user != null && !StringUtils.isEmpty(((OfficeUserDTO)user).getEmail())){
			return ((OfficeUserDTO)user).getEmail();
		}else {
			return CommonConstant.ANOYMOUS_USER_EMAIL;
		}
	}
	
	public static UserDetails getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			if(authentication.getPrincipal()!=null && authentication.getPrincipal() instanceof UserDetails){
				return (UserDetails)authentication.getPrincipal();
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}
