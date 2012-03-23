package com.pccw.springframework.dao;

import java.util.List;

import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.repository.EmailMessage;

public interface MessageManagementDAO {
	public List<EmailMessage> getMessagesForInbox(); 
	public void sendEmail(EmailMessage emailMessage);
	public List<EmailMessage> getMessagesForSearch(EmailMessagePagedCriteria pagedCriteria);
	public Integer getMessagesCountForSearch(EmailMessagePagedCriteria pagedCriteria);
	public EmailMessage getEmailMessageBySysRefMsg(String sysRefMsg);
}
