package com.pccw.springframework.dao;

import java.util.List;

import com.pccw.springframework.dto.OfficeRolePagedCriteria;
import com.pccw.springframework.repository.OfficeRole;

public interface OfficeRoleDAO {
	public List<OfficeRole> getOfficeRolesByCriteria(OfficeRolePagedCriteria pagedCriteria);
	public int getOfficeRolesCountByCriteria(OfficeRolePagedCriteria pagedCriteria);
	public OfficeRole getOfficeRoleBySysRefRole(String sysRefRole);
}
