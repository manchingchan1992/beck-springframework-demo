package com.pccw.springframework.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.repository.OfficeRole;
import com.pccw.springframework.repository.OfficeUser;

public class OfficeUserConvertor {
	
	public static OfficeUserDTO toDto(OfficeUser usr){
		OfficeUserDTO dto = new OfficeUserDTO();

		if(usr != null){
			BasePropertiesConvertor.toDto(usr, dto);
			
			dto.setLoginId(StringUtils.isEmpty(usr.getLoginId()) ? "" : usr.getLoginId());
			dto.setPassword(StringUtils.isEmpty(usr.getPassword()) ? "" : usr.getPassword());
			dto.setAccountStatus(StringUtils.isEmpty(usr.getAccountStatus()) ? "" : usr.getAccountStatus());
			dto.setEnName(StringUtils.isEmpty(usr.getEnName()) ? "" : usr.getEnName());
			dto.setCnName(StringUtils.isEmpty(usr.getCnName()) ? "" : usr.getCnName());
			dto.setEmail(StringUtils.isEmpty(usr.getEmail()) ? "" : usr.getEmail());
			
			List<OfficeRole> roles = usr.getRoles();
			List<OfficeRoleDTO> roleDtos = new ArrayList<OfficeRoleDTO>();
			
			if(!CollectionUtils.isEmpty(roles)){
				for(OfficeRole role : roles){
					roleDtos.add(OfficeRoleConvertor.toDto(role));
				}
			}
			
			dto.setRoles(roleDtos);
		}
		
		return dto;
	}
	
	public static OfficeUser toPo(OfficeUserDTO dto){
		OfficeUser usr = new OfficeUser();
		

		if(dto != null){
			BasePropertiesConvertor.toPo(usr, dto);
			
			usr.setLoginId(StringUtils.isEmpty(dto.getLoginId()) ? "" : dto.getLoginId());
			usr.setPassword(StringUtils.isEmpty(dto.getPassword()) ? "" : dto.getPassword());
			usr.setAccountStatus(StringUtils.isEmpty(dto.getAccountStatus()) ? "" : dto.getAccountStatus());
			usr.setEnName(StringUtils.isEmpty(dto.getEnName()) ? "" : dto.getEnName());
			usr.setCnName(StringUtils.isEmpty(dto.getCnName()) ? "" : dto.getCnName());
			usr.setEmail(StringUtils.isEmpty(dto.getEmail()) ? "" : dto.getEmail());
			
			List<OfficeRoleDTO> roleDtos = dto.getRoles();
			List<OfficeRole> roles = new ArrayList<OfficeRole>();
			
			if(!CollectionUtils.isEmpty(roleDtos)){
				for(OfficeRoleDTO roleDto : roleDtos){
					roles.add(OfficeRoleConvertor.toPo(roleDto));
				}
			}
			
			usr.setRoles(roles);
		}
		
		return usr;
	}
}
