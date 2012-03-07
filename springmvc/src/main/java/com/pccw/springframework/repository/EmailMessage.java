package com.pccw.springframework.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_MSG")
public class EmailMessage extends BaseEntity {
	private String sysRefMessage;
	private String messageFrom;
	private String messageTo;
	private String messageCc;
	private String messageTitle;
	private String messageContent;

	@Id
	@Column(name="SYS_REF_MSG")
	public String getSysRefMessage() {
		return sysRefMessage;
	}

	public void setSysRefMessage(String sysRefMessage) {
		this.sysRefMessage = sysRefMessage;
	}

	@Column(name="MSG_FROM")
	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	@Column(name="MSG_TO")
	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	@Column(name="MSG_CC")
	public String getMessageCc() {
		return messageCc;
	}

	public void setMessageCc(String messageCc) {
		this.messageCc = messageCc;
	}

	@Column(name="MSG_TITLE")
	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	@Column(name="MSG_CNT")
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

}
