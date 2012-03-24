package com.pccw.springframework.repository;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_OA_USR_ROLE_ASGN")
public class OfficeUserRoleAssign {

	private OfficeUserRoleAssignPK pk = new OfficeUserRoleAssignPK();

	@EmbeddedId
	public OfficeUserRoleAssignPK getPk() {
		return pk;
	}

	public void setPk(OfficeUserRoleAssignPK pk) {
		this.pk = pk;
	}

}
