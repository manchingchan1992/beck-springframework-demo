package com.pccw.springframework.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_OA_USR")
public class OfficeUser extends BaseEntity {
	private String userRecId;
	private String loginId;
	private String password;
	private String accountStatus;
	private String enName;
	private String cnName;
	private String email;
	
	private List<OfficeUserRoleAssign> roles = new ArrayList<OfficeUserRoleAssign>();

	@Column(name="USR_REC_ID")
	@Id
	public String getUserRecId() {
		return userRecId;
	}

	public void setUserRecId(String userRecId) {
		this.userRecId = userRecId;
	}

	@Column(name="LOGIN_ID")
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name="PWD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="ACC_ST")
	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name="ENM")
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name="CNM")
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.officeUser", cascade={CascadeType.ALL})
	public List<OfficeUserRoleAssign> getRoles() {
		return roles;
	}

	public void setRoles(List<OfficeUserRoleAssign> roles) {
		this.roles = roles;
	}	
}
