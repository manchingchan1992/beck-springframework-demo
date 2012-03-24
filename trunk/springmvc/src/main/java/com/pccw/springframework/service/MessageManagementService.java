package com.pccw.springframework.service;

import java.util.List;

import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;

public interface MessageManagementService {	
	public void sendEmail(EmailMessageDTO dto);
	public List<EmailMessageDTO> getMessagesForSearch(EmailMessagePagedCriteria pagedCriteria);
	public Integer getMessagesCountForSearch(EmailMessagePagedCriteria pagedCriteria);
	public EmailMessageDTO viewMessageDetail(String sysRefMessage,String boxType);
	public void deleteEmailMessage(String sysRefMsg , boolean foreverDelete);
}
