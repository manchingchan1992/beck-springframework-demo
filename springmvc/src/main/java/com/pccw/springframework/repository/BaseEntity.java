package com.pccw.springframework.repository;

import java.util.Date;

import javax.persistence.Column;

public class BaseEntity {
	private String createBy;
	private Date createDateTime;
	private String lastUpdateBy;
	private Date lastUpdateDateTime;
	private String lastUpdateTransaction;

	@Column(name="CR_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name="CR_DTTM")
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Column(name="LST_UPD_BY")
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	
	@Column(name="LST_UPD_DTTM")
	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	@Column(name="LST_TX_ACTN")
	public String getLastUpdateTransaction() {
		return lastUpdateTransaction;
	}

	public void setLastUpdateTransaction(String lastUpdateTransaction) {
		this.lastUpdateTransaction = lastUpdateTransaction;
	}
}
