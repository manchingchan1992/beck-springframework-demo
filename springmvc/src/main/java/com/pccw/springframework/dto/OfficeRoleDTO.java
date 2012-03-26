package com.pccw.springframework.dto;

public class OfficeRoleDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemRoleRefNum;

	private String roleId;

	private String roleName;

	private String roleDesc;

	public String getSystemRoleRefNum() {
		return systemRoleRefNum;
	}

	public void setSystemRoleRefNum(String systemRoleRefNum) {
		this.systemRoleRefNum = systemRoleRefNum;
	}

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
}
