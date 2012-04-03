package com.pccw.springframework.dto;

import java.io.Serializable;

public class OfficeUserEnquireDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String loginId;
	
	private String cnName;
	
	private String enName;
	
	private String email;
	
	private String accountStatus;
	
	private String role;
	
	private JmesaCheckBoxDTO jmesaDto;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public JmesaCheckBoxDTO getJmesaDto() {
		return jmesaDto;
	}

	public void setJmesaDto(JmesaCheckBoxDTO jmesaDto) {
		this.jmesaDto = jmesaDto;
	}
}
