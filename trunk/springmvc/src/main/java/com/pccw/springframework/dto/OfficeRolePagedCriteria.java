package com.pccw.springframework.dto;

import java.io.Serializable;

import com.pccw.springframework.helper.PagedCriteria;

public class OfficeRolePagedCriteria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	
	private String roleName;
	
	private String roleDesc;
	
	private String status;
	
	private PagedCriteria pagedCriteria = new PagedCriteria();

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PagedCriteria getPagedCriteria() {
		return pagedCriteria;
	}

	public void setPagedCriteria(PagedCriteria pagedCriteria) {
		this.pagedCriteria = pagedCriteria;
	}
}
