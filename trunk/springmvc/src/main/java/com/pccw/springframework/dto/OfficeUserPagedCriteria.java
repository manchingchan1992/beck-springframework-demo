package com.pccw.springframework.dto;

import java.io.Serializable;

import com.pccw.springframework.helper.PagedCriteria;

public class OfficeUserPagedCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String loginId;

	private String enName;

	private String cnName;

	private String email;

	private String accountStatus;

	private String role;

	private PagedCriteria pagedCriteria = new PagedCriteria();

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public PagedCriteria getPagedCriteria() {
		return pagedCriteria;
	}

	public void setPagedCriteria(PagedCriteria pagedCriteria) {
		this.pagedCriteria = pagedCriteria;
	}

}
