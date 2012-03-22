package com.pccw.springframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.springframework.convertor.OfficeUserConvertor;
import com.pccw.springframework.dao.OfficeUserDAO;
import com.pccw.springframework.repository.OfficeUser;

@Service(value="userDetailService")
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private OfficeUserDAO officeUserDao;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		OfficeUser user = officeUserDao.getOfficeUserByLoginId(name);
		
		if(user == null){
			throw new UsernameNotFoundException("The user with login id " + name + "not found !");
		}
		return OfficeUserConvertor.toDto(user);
	}

}
