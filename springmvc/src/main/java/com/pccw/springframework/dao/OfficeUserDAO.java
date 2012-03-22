package com.pccw.springframework.dao;

import com.pccw.springframework.repository.OfficeUser;

public interface OfficeUserDAO {
	public OfficeUser getOfficeUserByLoginId(String loginId);
}
