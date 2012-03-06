package com.pccw.springframework.dto;

import java.io.Serializable;

public class OfficeUserDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userRecId;
	private String loginId;
	private String password;
	private String accountStatus;
	private String enName;
	private String cnName;
	private String email;

	public String getUserRecId() {
		return userRecId;
	}

	public void setUserRecId(String userRecId) {
		this.userRecId = userRecId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
