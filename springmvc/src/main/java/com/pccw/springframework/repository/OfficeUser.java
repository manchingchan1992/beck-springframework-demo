package com.pccw.springframework.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	private List<OfficeRole> roles = new ArrayList<OfficeRole>();

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

	@ManyToMany(fetch=FetchType.EAGER , cascade={CascadeType.ALL})
	@JoinTable(
			name="T_OA_USR_ROLE_ASGN",
			joinColumns={@JoinColumn(name="USR_REC_ID")},
			inverseJoinColumns={@JoinColumn(name="SYS_REF_ROLE")}
	)
	public List<OfficeRole> getRoles() {
		return roles;
	}

	public void setRoles(List<OfficeRole> roles) {
		this.roles = roles;
	}
}
