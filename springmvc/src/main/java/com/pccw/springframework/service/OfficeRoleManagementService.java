package com.pccw.springframework.service;

import java.util.List;

import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeRolePagedCriteria;

public interface OfficeRoleManagementService {
	public List<OfficeRoleDTO> searchRolesByCriteria(OfficeRolePagedCriteria pagedCriteria);
	public int getOfficeRolesCountByCriteria(OfficeRolePagedCriteria pagedCriteria);
	public OfficeRoleDTO getOfficeRoleBySystemRefNum(String sysRefRole);
}
