package com.pccw.springframework.service;

import java.util.List;

import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.dto.OfficeUserPagedCriteria;

public interface OfficeUserManagementService {
	public OfficeUserDTO getOfficeUserByEmail(String email);
	public List<OfficeRoleDTO> getAvailableRoles();
	public List<OfficeUserDTO> searchUsersByCriteria(OfficeUserPagedCriteria pagedCriteria);
	public int getUsersCountByCriteria(OfficeUserPagedCriteria pagedCriteria);
}
