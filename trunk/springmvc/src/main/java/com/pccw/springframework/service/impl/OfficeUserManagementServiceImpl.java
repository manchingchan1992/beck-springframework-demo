package com.pccw.springframework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.convertor.OfficeRoleConvertor;
import com.pccw.springframework.convertor.OfficeUserConvertor;
import com.pccw.springframework.dao.OfficeUserDAO;
import com.pccw.springframework.dto.OfficeRoleDTO;
import com.pccw.springframework.dto.OfficeUserDTO;
import com.pccw.springframework.dto.OfficeUserPagedCriteria;
import com.pccw.springframework.repository.OfficeRole;
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
	
	@Transactional(readOnly=true)
	public List<OfficeRoleDTO> getAvailableRoles(){
		List<OfficeRole> roles = officeUserDao.getAvailableRoles();
		List<OfficeRoleDTO> dtos = new ArrayList<OfficeRoleDTO>();
		
		if(!CollectionUtils.isEmpty(roles)){
			for(OfficeRole role : roles){
				dtos.add(OfficeRoleConvertor.toDto(role));
			}
		}
		
		return dtos;
	}
	
	@Transactional(readOnly=true)
	public List<OfficeUserDTO> searchUsersByCriteria(OfficeUserPagedCriteria pagedCriteria){
		List<OfficeUser> users = officeUserDao.searchUsersByCriteria(pagedCriteria);
		List<OfficeUserDTO> dtos = new ArrayList<OfficeUserDTO>();
		
		if(!CollectionUtils.isEmpty(users)){
			for(OfficeUser user : users){
				dtos.add(OfficeUserConvertor.toDto(user, false));
			}
		}
		return dtos;
	}
	
	@Transactional(readOnly=true)
	public int getUsersCountByCriteria(OfficeUserPagedCriteria pagedCriteria){
		return officeUserDao.getUsersCountByCriteria(pagedCriteria);
	}
	
	@Transactional(readOnly=true)
	public OfficeUserDTO getUserByUserRecId(String usrRecId){
		return OfficeUserConvertor.toDto(officeUserDao.getUserByUsrRecId(usrRecId), true);
	}
	
	@Transactional(readOnly=true)
	public OfficeUserDTO getUserByLoginId(String loginId){
		return OfficeUserConvertor.toDto(officeUserDao.getOfficeUserByLoginId(loginId), false);
	}
	
	public void updateUser(OfficeUserDTO officeUserDto){
		officeUserDao.updateUser(OfficeUserConvertor.toPo(officeUserDto));
	}
}
