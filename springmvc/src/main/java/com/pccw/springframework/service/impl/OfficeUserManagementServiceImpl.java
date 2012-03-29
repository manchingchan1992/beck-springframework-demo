package com.pccw.springframework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.springframework.convertor.OfficeUserConvertor;
import com.pccw.springframework.dao.OfficeUserDAO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.repository.OfficeUser;
import com.pccw.springframework.service.OfficeUserManagementService;

@Service("officeUsrMgmtService")
@Transactional(propagation=Propagation.REQUIRED)
public class OfficeUserManagementServiceImpl implements OfficeUserManagementService{
	
	@Autowired
	private OfficeUserDAO officeUserDao;

	@Transactional(readOnly=true)
	public OfficeUserDTO getOfficeUserByEmail(String email) {
		OfficeUser user = officeUserDao.getOfficeUserByEmail(email);
		return OfficeUserConvertor.toDto(user , false);
	}
	
}
