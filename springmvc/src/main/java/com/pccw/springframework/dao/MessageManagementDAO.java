package com.pccw.springframework.dao;

import java.util.List;

import com.pccw.springframework.repository.EmailMessage;

public interface MessageManagementDAO {
	public List<EmailMessage> getMessagesForInbox(); 
}
