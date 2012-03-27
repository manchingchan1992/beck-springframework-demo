package com.pccw.springframework.convertor;

import org.apache.commons.lang.StringUtils;

import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.repository.OfficeRole;

public class OfficeRoleConvertor {
	
	public static OfficeRoleDTO toDto (OfficeRole po){
		
		if(po == null){
			return null;
		}
		
		OfficeRoleDTO dto = new OfficeRoleDTO();
		
		BasePropertiesConvertor.toDto(po, dto);
		
		dto.setSystemRoleRefNum(StringUtils.isEmpty(po.getSytemRoleRefNum()) ? "" : po.getSytemRoleRefNum());
		dto.setRoleId(StringUtils.isEmpty(po.getRoleId()) ? "" : po.getRoleId());
		dto.setRoleName(StringUtils.isEmpty(po.getRoleName()) ? "" : po.getRoleName());
		dto.setRoleDesc(StringUtils.isEmpty(po.getRoleDesc()) ? "" : po.getRoleDesc());
		
		return dto;
	}
	
	public static OfficeRole toPo (OfficeRoleDTO dto){
		
		if(dto == null){
			return null;
		}
		
		OfficeRole po = new OfficeRole();
		
		BasePropertiesConvertor.toPo(po, dto);
		
		po.setSytemRoleRefNum(StringUtils.isEmpty(dto.getSystemRoleRefNum()) ? "" : dto.getSystemRoleRefNum());
		po.setRoleId(StringUtils.isEmpty(dto.getRoleId()) ? "" : dto.getRoleId());
		po.setRoleName(StringUtils.isEmpty(dto.getRoleName()) ? "" : dto.getRoleName());
		po.setRoleDesc(StringUtils.isEmpty(dto.getRoleDesc()) ? "" : dto.getRoleDesc());
		
		return po;
	}
}
