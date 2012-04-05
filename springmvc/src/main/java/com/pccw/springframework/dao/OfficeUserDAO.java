package com.pccw.springframework.dao;

import java.util.List;

import com.pccw.springframework.dto.OfficeUserPagedCriteria;
import com.pccw.springframework.repository.OfficeRole;
import com.pccw.springframework.repository.OfficeUser;

public interface OfficeUserDAO {
	public OfficeUser getOfficeUserByLoginId(String loginId);
	public OfficeUser getOfficeUserByEmail(String email);
	public List<OfficeRole> getAvailableRoles();
	public List<OfficeUser> searchUsersByCriteria(OfficeUserPagedCriteria pagedCriteria);
	public int getUsersCountByCriteria(OfficeUserPagedCriteria pagedCriteria);
	public OfficeUser getUserByUsrRecId(String usrRecId);
	public void updateUser(OfficeUser usr);
}
