package com.pccw.springframework.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.dto.OfficeUserEnquireDTO;
import com.pccw.springframework.dto.OfficeUserPagedCriteria;
import com.pccw.springframework.repository.OfficeRole;
import com.pccw.springframework.repository.OfficeUser;

public class OfficeUserConvertor {
	
	public static OfficeUserDTO toDto(OfficeUser usr , boolean needConvertRoles){
		
		if(usr == null){
			return null;
		}
		
		OfficeUserDTO dto = new OfficeUserDTO();
		
		BasePropertiesConvertor.toDto(usr, dto);
		
		dto.setUserRecId(StringUtils.isEmpty(usr.getUserRecId()) ? "" : usr.getUserRecId());
		dto.setLoginId(StringUtils.isEmpty(usr.getLoginId()) ? "" : usr.getLoginId());
		dto.setPassword(StringUtils.isEmpty(usr.getPassword()) ? "" : usr.getPassword());
		dto.setAccountStatus(StringUtils.isEmpty(usr.getAccountStatus()) ? "" : usr.getAccountStatus());
		dto.setEnName(StringUtils.isEmpty(usr.getEnName()) ? "" : usr.getEnName());
		dto.setCnName(StringUtils.isEmpty(usr.getCnName()) ? "" : usr.getCnName());
		dto.setEmail(StringUtils.isEmpty(usr.getEmail()) ? "" : usr.getEmail());
		
		if(true){			
			List<OfficeRole> roles = usr.getRoles();
			List<OfficeRoleDTO> roleDtos = new ArrayList<OfficeRoleDTO>();
			
			if(roles != null && roles.size() != 0){
				for(OfficeRole role : roles){
					roleDtos.add(OfficeRoleConvertor.toDto(role));
				}
			}
			
			dto.setRoles(roleDtos);
		}

		return dto;
	}
	
	public static OfficeUser toPo(OfficeUserDTO dto){
		
		if(dto == null){
			return null;
		}
		
		OfficeUser usr = new OfficeUser();
		
		BasePropertiesConvertor.toPo(usr, dto);
		
		usr.setUserRecId(StringUtils.isEmpty(dto.getUserRecId()) ? "" : dto.getUserRecId());
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
		
		return usr;
	}
	
	public static OfficeUserPagedCriteria toPagedCriteria(OfficeUserEnquireDTO officeUserEnquireDto){
		OfficeUserPagedCriteria pagedCriteria = new OfficeUserPagedCriteria();
		
		if(officeUserEnquireDto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(officeUserEnquireDto , pagedCriteria);
		
		if(officeUserEnquireDto.getJmesaDto() != null && officeUserEnquireDto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPagedCriteria().getPageFilter().setRowStart(officeUserEnquireDto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPagedCriteria().getPageFilter().setRowEnd(officeUserEnquireDto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
}
