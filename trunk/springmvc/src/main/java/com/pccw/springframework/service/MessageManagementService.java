package com.pccw.springframework.service;

import java.util.List;

import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;

public interface MessageManagementService {	
	public void sendEmail(EmailMessageDTO dto);
	public List<EmailMessageDTO> getMessagesForOutbox(EmailMessagePagedCriteria pagedCriteria);
	public Integer getMessagesCountForOutBox(EmailMessagePagedCriteria pagedCriteria);
}
