package com.pccw.springframework.convertor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeRolePagedCriteria;
import com.pccw.springframework.repository.OfficeRole;
import com.pccw.springframework.utility.StringEncodeUtility;

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
		dto.setStatus(StringUtils.isEmpty(po.getStatus()) ? "" : po.getStatus());
		dto.setEncodedSysRefNum(StringEncodeUtility.encode(po.getSytemRoleRefNum(), CommonConstant.STRING_ENCODE_BY_DEFAULT));
		
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
		po.setStatus(StringUtils.isEmpty(dto.getStatus()) ? CommonConstant.INACTIVE : dto.getStatus());
		
		return po;
	}
	
	public static OfficeRolePagedCriteria toPagedCriteria(OfficeRoleDTO dto){
		OfficeRolePagedCriteria pagedCriteria = new OfficeRolePagedCriteria();
		
		if(dto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		
		if(dto.getJmesaDto() != null && dto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(dto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(dto.getJmesaDto().getRowSelect().getRowEnd());
		}
		return pagedCriteria;
	}
}
