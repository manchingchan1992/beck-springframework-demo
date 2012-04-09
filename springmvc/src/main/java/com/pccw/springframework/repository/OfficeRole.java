package com.pccw.springframework.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_OA_ROLE")
public class OfficeRole extends BaseEntity {
	private String sytemRoleRefNum;
	private String roleId;
	private String roleName;
	private String roleDesc;
	private String status;

	@Id
	@Column(name="SYS_REF_ROLE", length=3)
	public String getSytemRoleRefNum() {
		return sytemRoleRefNum;
	}

	public void setSytemRoleRefNum(String sytemRoleRefNum) {
		this.sytemRoleRefNum = sytemRoleRefNum;
	}

	@Column(name="ROLE_ID", length=20, nullable=false, unique=true)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name="ROLE_NAME", length=20, nullable=false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="ROLE_DESC", length=30, nullable=true)
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name="ROLE_STAT" , length=2 ,nullable=false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
