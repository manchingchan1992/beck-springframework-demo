package com.pccw.springframework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.springframework.convertor.OfficeUserConvertor;
import com.pccw.springframework.dao.OfficeUserDAO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.service.OfficeUserManagementService;

@Service("officeUsrMgmtService")
public class OfficeUserManagementServiceImpl implements OfficeUserManagementService{
	
	@Autowired
	private OfficeUserDAO officeUserDao;

	public OfficeUserDTO getOfficeUserByEmail(String email) {
		return OfficeUserConvertor.toDto(officeUserDao.getOfficeUserByEmail(email));
	}
	
}
