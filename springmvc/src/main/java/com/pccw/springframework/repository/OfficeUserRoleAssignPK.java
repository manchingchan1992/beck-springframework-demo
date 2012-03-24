package com.pccw.springframework.repository;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OfficeUserRoleAssignPK {

	private OfficeUser officeUser;
	private OfficeRole officeRole;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USR_REC_ID",referencedColumnName="USR_REC_ID",nullable=false)
	public OfficeUser getOfficeUser() {
		return officeUser;
	}

	public void setOfficeUser(OfficeUser officeUser) {
		this.officeUser = officeUser;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SYS_REF_ROLE", referencedColumnName="SYS_REF_ROLE", nullable=false)
	public OfficeRole getOfficeRole() {
		return officeRole;
	}

	public void setOfficeRole(OfficeRole officeRole) {
		this.officeRole = officeRole;
	}

}
