package com.pccw.springframework.service;

import com.pccw.springframework.dto.OfficeUserDTO;

public interface OfficeUserManagementService {
	public OfficeUserDTO getOfficeUserByEmail(String email);
}
