package com.pccw.springframework.dto;

import java.io.Serializable;

public class EmailMessageEnquireDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sysRefMessage;
	
	private String messageFrom;
	
	private String messageTo;
	
	private String messageCc;
	
	private String messageTitle;
	
	private String messageContent;
	
	private String isRead;
	
	private JmesaCheckBoxDTO jmesaDto = new JmesaCheckBoxDTO();

	public String getSysRefMessage() {
		return sysRefMessage;
	}

	public void setSysRefMessage(String sysRefMessage) {
		this.sysRefMessage = sysRefMessage;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	public String getMessageCc() {
		return messageCc;
	}

	public void setMessageCc(String messageCc) {
		this.messageCc = messageCc;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public JmesaCheckBoxDTO getJmesaDto() {
		return jmesaDto;
	}

	public void setJmesaDto(JmesaCheckBoxDTO jmesaDto) {
		this.jmesaDto = jmesaDto;
	}
}
