package com.pccw.springframework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.convertor.OfficeRoleConvertor;
import com.pccw.springframework.dao.OfficeRoleDAO;
import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeRolePagedCriteria;
import com.pccw.springframework.repository.OfficeRole;
import com.pccw.springframework.service.OfficeRoleManagementService;

@Service("officeRoleMgmtService")
@Transactional
public class OfficeRoleManagementServiceImpl implements OfficeRoleManagementService {
	
	@Autowired
	private OfficeRoleDAO officeRoleDao;
	
	@Transactional(readOnly=true)
	public OfficeRoleDTO getOfficeRoleBySystemRefNum(String sysRefRole) {
		return OfficeRoleConvertor.toDto(officeRoleDao.getOfficeRoleBySysRefRole(sysRefRole));
	}

	@Transactional(readOnly=true)
	public List<OfficeRoleDTO> searchRolesByCriteria(
			OfficeRolePagedCriteria pagedCriteria) {
		List<OfficeRole> roles = officeRoleDao.getOfficeRolesByCriteria(pagedCriteria);
		List<OfficeRoleDTO> dtos = new ArrayList<OfficeRoleDTO>();
		
		if(!CollectionUtils.isEmpty(roles)){
			for(OfficeRole role : roles){
				dtos.add(OfficeRoleConvertor.toDto(role));
			}
		}
		return dtos;
	}

	public int getOfficeRolesCountByCriteria(
			OfficeRolePagedCriteria pagedCriteria) {
		return officeRoleDao.getOfficeRolesCountByCriteria(pagedCriteria);
	}
}
