package com.pccw.springframework.dto;

import java.io.Serializable;

import com.pccw.springframework.helper.PagedCriteria;

public class EmailMessagePagedCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String messageFrom;
	private String messageTo;
	private String messageCc;
	private PagedCriteria pagedCriteria = new PagedCriteria();

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

	public PagedCriteria getPagedCriteria() {
		return pagedCriteria;
	}

	public void setPagedCriteria(PagedCriteria pagedCriteria) {
		this.pagedCriteria = pagedCriteria;
	}
}
